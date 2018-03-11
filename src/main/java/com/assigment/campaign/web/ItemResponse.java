package com.assigment.campaign.web;

import com.assigment.campaign.dto.DiscountedItem;

import java.util.List;

public class ItemResponse {
    private List<DiscountedItem> items;

    protected ItemResponse() {

    }

    public ItemResponse(List<DiscountedItem> discountedItems) {
        items=discountedItems;
    }

    public List<DiscountedItem> getItems() {
        return items;
    }

    public void setItems(List<DiscountedItem> items) {
        this.items = items;
    }
}
