package com.assigment.campaign.domain;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class CategoryCampaign extends Campaign {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column(name = "CATEGORY_ID",insertable = false,updatable = false)
    private Long categoryId;

    public Category getCategory() {
        return category;
    }

    protected void setCategory(Category category) {
        this.category = category;
    }
}
