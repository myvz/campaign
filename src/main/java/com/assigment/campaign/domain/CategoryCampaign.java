package com.assigment.campaign.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CategoryCampaign extends Campaign {

    @OneToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Category getCategory() {
        return category;
    }

    protected void setCategory(Category category) {
        this.category = category;
    }
}
