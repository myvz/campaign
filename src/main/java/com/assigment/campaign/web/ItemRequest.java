package com.assigment.campaign.web;

import com.assigment.campaign.dto.Item;

import java.util.List;

public class ItemRequest {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
