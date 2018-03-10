package com.assigment.campaign.service;

import com.assigment.campaign.dto.DiscountedItem;
import com.assigment.campaign.dto.Item;

import java.util.List;

public interface DiscountService {
    List<DiscountedItem> calcDiscount(List<Item> items);
}
