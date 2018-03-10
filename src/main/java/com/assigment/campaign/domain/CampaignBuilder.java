package com.assigment.campaign.domain;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class CampaignBuilder<C extends CampaignBuilder,T extends Campaign> {

    protected T campaign;


    public CampaignBuilder(T campaign) {
        this.campaign = campaign;
    }

    public C discount(BigDecimal discount) {
        campaign.setDiscount(discount);
        return (C) this;
    }

    public C discountType(DiscountType discountType) {
        campaign.setDiscountType(discountType);
        return (C) this;
    }

    public C maxDiscountAmount(BigDecimal maxDiscountAmount) {
        campaign.setMaxDiscountAmount(maxDiscountAmount);
        return (C) this;
    }

    public C name(String name) {
        campaign.setName(name);
        return (C) this;
    }

    public T build() {
        Objects.requireNonNull(this.campaign.getName(),"Name cannot be null");
        Objects.requireNonNull(this.campaign.getDiscount(),"Discount cannot be null");
        Objects.requireNonNull(this.campaign.getDiscountType(),"DiscountType cannot be null");
        if (this.campaign.getDiscountType().equals(DiscountType.RATE)) {
            Objects.requireNonNull(this.campaign.getMaxDiscountAmount(),"MaxDiscountAmount can not be null for RATE dicountType");
            //TOOO Rate 100 kontrolu
        }
        return campaign;
    }
}
