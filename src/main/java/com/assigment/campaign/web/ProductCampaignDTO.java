package com.assigment.campaign.web;

import com.assigment.campaign.domain.ProductCampaign;
import com.assigment.campaign.domain.DiscountType;

import java.math.BigDecimal;

public class ProductCampaignDTO {

    private String name;
    private Long productId;
    private DiscountType discountType;
    private BigDecimal maxDiscountAmount;
    private BigDecimal discount;

    public static ProductCampaignDTO mapFrom(ProductCampaign productCampaign) {
        ProductCampaignDTO productCampaignDTO = new ProductCampaignDTO();
        productCampaignDTO.setDiscount(productCampaign.getDiscount());
        productCampaignDTO.setDiscountType(productCampaign.getDiscountType());
        productCampaignDTO.setMaxDiscountAmount(productCampaign.getMaxDiscountAmount());
        productCampaignDTO.setProductId(productCampaign.getProduct().getId());
        productCampaignDTO.setName(productCampaign.getName());
        return productCampaignDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
