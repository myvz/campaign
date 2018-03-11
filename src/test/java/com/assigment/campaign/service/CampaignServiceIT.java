package com.assigment.campaign.service;

import com.assigment.campaign.domain.*;
import com.assigment.campaign.repository.CampaignRepository;
import com.assigment.campaign.repository.CategoryCampaignRepository;
import com.assigment.campaign.repository.ProductCampaignRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampaignServiceIT {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ProductCampaignRepository productCampaignRepository;

    @Autowired
    private CategoryCampaignRepository categoryCampaignRepository;

    @Before
    public void before() {
        CategoryCampaign categoryCampaign = new CategoryCampaignBuilder()
                .name("Kazaklar Daha ucuz")
                .category(new Category(100L, "Kazak"))
                .discountType(DiscountType.RATE)
                .discount(BigDecimal.valueOf(10.00))
                .maxDiscountAmount(BigDecimal.valueOf(30))
                .build();
        ProductCampaign productCampaign = new ProductCampaignBuilder()
                .name("Mavi Tişort Indırımde")
                .product(new Product(10L,"Mavi Tişort"))
                .discountType(DiscountType.PRICE)
                .discount(BigDecimal.valueOf(50))
                .build();
        campaignService.save(categoryCampaign);
        campaignService.save(productCampaign);
    }

    @Test
    public void testSave() {
        Optional<CategoryCampaign> byCategoryId = categoryCampaignRepository.findByCategoryId(100L);
        Optional<ProductCampaign> byProductId = productCampaignRepository.findByProductId(10L);
        assertTrue(byCategoryId.isPresent());
        assertTrue(byProductId.isPresent());
    }

    @Test
    public void testUpdate() {
        Optional<CategoryCampaign> byCategoryId = categoryCampaignRepository.findByCategoryId(100L);
        byCategoryId.ifPresent(categoryCampaign -> {
            categoryCampaign.setName("Gelinle Kaynanayı Barıştıran İndirim...");
            campaignService.save(categoryCampaign);
        });
        byCategoryId = categoryCampaignRepository.findByCategoryId(100L);
        assertEquals("Gelinle Kaynanayı Barıştıran İndirim...",byCategoryId.get().getName());
    }

    @After
    public void tearDown() throws Exception {
        campaignService.delete(categoryCampaignRepository.findByCategoryId(100L).get());
        campaignService.delete(productCampaignRepository.findByProductId(10L).get());
    }
}