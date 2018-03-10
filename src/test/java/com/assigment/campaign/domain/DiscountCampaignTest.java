package com.assigment.campaign.domain;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DiscountCampaignTest {

    private Category category;
    private CategoryCampaignBuilder builder;

    @Before
    public void setUp() throws Exception {
        category = new Category(100L, "Kazak");
        builder = new CategoryCampaignBuilder();
    }

    @Test
    public void calculateDiscountedPriceWhenDiscountTypePrice() {
        CategoryCampaign campaign = builder.name("Kazaklar Daha ucuz")
                .category(category)
                .discountType(DiscountType.PRICE)
                .discount(BigDecimal.valueOf(10.00))
                .build();
        BigDecimal discount = campaign.calculateDiscountedPrice(BigDecimal.valueOf(100.00));
        assertThat(discount,Matchers.comparesEqualTo(BigDecimal.valueOf(90)));
    }


    @Test
    public void calculateDiscountedPriceWhenRateNotReachedMaxDiscountAmount() {
        CategoryCampaign campaign = builder.name("Kazaklar Daha ucuz")
                .category(category)
                .discountType(DiscountType.RATE)
                .discount(BigDecimal.valueOf(10.00))
                .maxDiscountAmount(BigDecimal.valueOf(30))
                .build();
        BigDecimal discount = campaign.calculateDiscountedPrice(BigDecimal.valueOf(100.00));
        assertThat(discount, Matchers.comparesEqualTo(BigDecimal.valueOf(90)));
    }

    @Test
    public void calculateDiscountedPriceWhenRateExceedsMaxDiscountAmount() {
        CategoryCampaign campaign = builder.name("Kazaklar Daha ucuz")
                .category(category)
                .discountType(DiscountType.RATE)
                .discount(BigDecimal.valueOf(10.00))
                .maxDiscountAmount(BigDecimal.valueOf(30))
                .build();
        BigDecimal discount = campaign.calculateDiscountedPrice(BigDecimal.valueOf(500));
        assertThat(discount,Matchers.comparesEqualTo(BigDecimal.valueOf(470)));
    }


}