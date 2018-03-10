package com.assigment.campaign.domain;

import java.util.Objects;

public class CategoryCampaignBuilder extends CampaignBuilder<CategoryCampaignBuilder,CategoryCampaign> {

    public CategoryCampaignBuilder() {
        super(new CategoryCampaign());
    }

    public CategoryCampaignBuilder category(Category category) {
        super.campaign.setCategory(category);
        return this;
    }

    public CategoryCampaign build() {
        Objects.requireNonNull(super.campaign.getCategory(),"Category cannot be null");
        return super.build();
    }
}
