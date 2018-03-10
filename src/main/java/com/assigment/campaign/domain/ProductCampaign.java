package com.assigment.campaign.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ProductCampaign extends Campaign {

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Product getProduct() {
        return product;
    }

    protected void setProduct(Product product) {
        this.product = product;
    }
}
