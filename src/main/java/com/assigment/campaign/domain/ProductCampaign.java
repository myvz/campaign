package com.assigment.campaign.domain;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ProductCampaign extends Campaign {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "PRODUCT_ID",insertable = false,updatable = false)
    private Long productId;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductId() {
        return productId;
    }
}
