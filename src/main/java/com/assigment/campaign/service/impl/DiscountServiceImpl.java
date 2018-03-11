package com.assigment.campaign.service.impl;

import com.assigment.campaign.dto.DiscountedItem;
import com.assigment.campaign.dto.Item;
import com.assigment.campaign.repository.CategoryCampaignRepository;
import com.assigment.campaign.repository.ProductCampaignRepository;
import com.assigment.campaign.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DiscountServiceImpl implements DiscountService {

    private CategoryCampaignRepository categoryCampaignRepository;

    private ProductCampaignRepository productCampaignRepository;

    public DiscountServiceImpl(CategoryCampaignRepository categoryCampaignRepository,
                               ProductCampaignRepository productCampaignRepository) {
        this.categoryCampaignRepository = categoryCampaignRepository;
        this.productCampaignRepository = productCampaignRepository;
    }

    @Override
    public List<DiscountedItem> calcDiscount(List<Item> items) {

        List<DiscountedItem> discountedItems = items.stream()
                .map(DiscountedItem::createFromItem)
                .collect(toList());
        applyCategoryCampaign(discountedItems);
        applyProductCampaign(discountedItems);
        return discountedItems;
    }

    private void applyCategoryCampaign(List<DiscountedItem> discountedItems) {
        discountedItems.stream()
                .collect(groupingBy(DiscountedItem::getCategoryId)).keySet()
                .stream()
                .forEach(key -> {
                    discountedItems.stream()
                            .collect(groupingBy(DiscountedItem::getCategoryId)).get(key)
                            .stream()
                            .max(Comparator.comparing(DiscountedItem::getPrice))
                            .ifPresent(discountedItem -> {
                                categoryCampaignRepository.findByCategoryId(key)
                                        .ifPresent(categoryCampaign -> {
                                            BigDecimal discountedPrice = categoryCampaign.calculateDiscountedPrice(discountedItem.getDiscountedPrice());
                                            discountedItem.setDiscountedPrice(discountedPrice);
                                        });
                            });
                });
    }

    private void applyProductCampaign(List<DiscountedItem> discountedItems) {
        discountedItems.forEach(discountedItem -> {
            productCampaignRepository.findByProductId(discountedItem.getProductId())
                    .ifPresent(productCampaign -> {
                        BigDecimal discountedPrice = productCampaign.calculateDiscountedPrice(discountedItem.getDiscountedPrice());
                        discountedItem.setDiscountedPrice(discountedPrice);
                    });
        });
    }
}
