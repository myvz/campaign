package com.assigment.campaign.web;

import com.assigment.campaign.domain.CategoryCampaign;
import com.assigment.campaign.domain.DiscountType;
import java.math.BigDecimal;

public class CategoryCampaignDTO {

    private String name;
    private Long categoryId;
    private DiscountType discountType;
    private BigDecimal maxDiscountAmount;
    private BigDecimal discount;

    public static CategoryCampaignDTO mapFrom(CategoryCampaign categoryCampaign) {
        CategoryCampaignDTO categoryCampaignDTO = new CategoryCampaignDTO();
        categoryCampaignDTO.setDiscount(categoryCampaign.getDiscount());
        categoryCampaignDTO.setDiscountType(categoryCampaign.getDiscountType());
        categoryCampaignDTO.setMaxDiscountAmount(categoryCampaign.getMaxDiscountAmount());
        categoryCampaignDTO.setCategoryId(categoryCampaign.getCategory().getId());
        categoryCampaignDTO.setName(categoryCampaign.getName());
        return categoryCampaignDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
