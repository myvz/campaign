package com.assigment.campaign.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Inheritance(strategy = InheritanceType.JOINED)
@javax.persistence.Entity
public abstract class Campaign extends Entity implements DiscountCampaign {

    private static final BigDecimal BIG_DECIMAL_100=BigDecimal.valueOf(100.00);

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Column(precision = 18, scale = 2)
    private BigDecimal discount;

    @Column(precision = 18, scale = 2)
    private BigDecimal maxDiscountAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal price) {
        if (discountType.equals(DiscountType.RATE)) {
            return calcDiscountForRate(price);
        } else if (discountType.equals(DiscountType.PRICE)) {
            return price.subtract(discount);
        } else  {
            throw new UnsupportedOperationException("Not implemented for"+ discountType);
        }
    }

    private BigDecimal calcDiscountForRate(BigDecimal price) {
        BigDecimal discountedAmount = price.multiply(discount).divide(BIG_DECIMAL_100);
        if (discountedAmount.compareTo(maxDiscountAmount)>0) {
            return price.subtract(maxDiscountAmount);
        }
        return price.subtract(discountedAmount);
    }
}
