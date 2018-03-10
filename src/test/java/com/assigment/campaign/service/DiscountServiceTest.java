package com.assigment.campaign.service;

import com.assigment.campaign.domain.*;
import com.assigment.campaign.dto.DiscountedItem;
import com.assigment.campaign.dto.Item;
import com.assigment.campaign.repository.CategoryCampaignRepository;
import com.assigment.campaign.repository.ProductCampainRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    List<Item> items = new ArrayList<>();


    DiscountServiceImpl discountService;

    @Before
    public void setUp() throws Exception {
        CategoryCampaignRepository categoryCampaignRepository = mock(CategoryCampaignRepository.class);
        ProductCampainRepository productCampainRepository = mock(ProductCampainRepository.class);
        discountService=new DiscountServiceImpl(categoryCampaignRepository, productCampainRepository);
        ProductCampaign campaign = new ProductCampaignBuilder()
                .name("Ucuz ayfon")
                .product(new Product(5L,"Ayfon"))
                .discountType(DiscountType.RATE)
                .discount(BigDecimal.valueOf(5))
                .maxDiscountAmount(BigDecimal.valueOf(100))
                .build();
        ProductCampaign campaign2 = new ProductCampaignBuilder()
                .name("Mavi Tişort Indırımde")
                .product(new Product(10L,"Mavi Tişort"))
                .discountType(DiscountType.PRICE)
                .discount(BigDecimal.valueOf(50))
                .build();
        CategoryCampaign campaign3 = new CategoryCampaignBuilder()
                .name("Kazaklar Daha ucuz")
                .category(new Category(100L,"Kazak"))
                .discountType(DiscountType.RATE)
                .discount(BigDecimal.valueOf(10.00))
                .maxDiscountAmount(BigDecimal.valueOf(30))
                .build();
        CategoryCampaign campaign4 = new CategoryCampaignBuilder()
                .name("Gömlekler Daha ucuz")
                .category(new Category(200L,"Gömlek"))
                .discountType(DiscountType.PRICE)
                .discount(BigDecimal.valueOf(20))
                .build();
        when(productCampainRepository.findByProduct(5L)).thenReturn(Optional.of(campaign));
        when(productCampainRepository.findByProduct(10L)).thenReturn(Optional.of(campaign2));
        when(categoryCampaignRepository.findByCategory(100L)).thenReturn(Optional.of(campaign3));
        when(categoryCampaignRepository.findByCategory(200L)).thenReturn(Optional.of(campaign4));

        items.clear();
        items.add(new Item(10L, 200L, BigDecimal.valueOf(100.99)));
        items.add(new Item(5L, 100L, BigDecimal.valueOf(200)));
        items.add(new Item(20L, 100L, BigDecimal.valueOf(500)));
    }

    @Test
    public void calcDiscount() {
        List<DiscountedItem> discountedItems = discountService.calcDiscount(items);
        assertThat(discountedItems.get(0).getDiscountedPrice(), Matchers.comparesEqualTo(BigDecimal.valueOf(30.99)));
        assertThat(discountedItems.get(1).getDiscountedPrice(), Matchers.comparesEqualTo(BigDecimal.valueOf(190)));
        assertThat(discountedItems.get(2).getDiscountedPrice(), Matchers.comparesEqualTo(BigDecimal.valueOf(470)));
    }
}
