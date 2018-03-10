package com.assigment.campaign.dto;

import java.math.BigDecimal;

public class DiscountedItem {

    private Long productId;
    private Long categoryId;
    private BigDecimal price;
    private BigDecimal discountedPrice;

    public static DiscountedItem createFromItem(Item item) {
        DiscountedItem discountedItem = new DiscountedItem();
        discountedItem.setProductId(item.getProductId());
        discountedItem.setCategoryId(item.getCategoryId());
        discountedItem.setPrice(item.getPrice());
        discountedItem.setDiscountedPrice(item.getPrice());
        return discountedItem;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
