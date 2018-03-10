package com.assigment.campaign.domain;

import java.util.Objects;

public class ProductCampaignBuilder extends CampaignBuilder<ProductCampaignBuilder,ProductCampaign> {

    public ProductCampaignBuilder() {
        super(new ProductCampaign());
    }

    public ProductCampaignBuilder product(Product product) {
        super.campaign.setProduct(product);
        return this;
    }

    public ProductCampaign build() {
        Objects.requireNonNull(super.campaign.getProduct(),"Product cannot be null");
        return super.build();
    }
}
