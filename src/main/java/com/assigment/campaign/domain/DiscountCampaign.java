package com.assigment.campaign.domain;

import java.math.BigDecimal;

public interface DiscountCampaign {

    BigDecimal calculateDiscountedPrice(BigDecimal price);
}
