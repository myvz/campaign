package com.assigment.campaign.dto;

import java.math.BigDecimal;

public class Item {
    private Long productId;
    private Long categoryId;
    private BigDecimal price;

    protected Item() {

    }

    public Item(Long productId, Long categoryId, BigDecimal price) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.price = price;
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
}
