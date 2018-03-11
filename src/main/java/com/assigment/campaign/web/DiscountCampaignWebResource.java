package com.assigment.campaign.web;

import com.assigment.campaign.dto.DiscountedItem;
import com.assigment.campaign.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calculateDiscounts")
public class DiscountCampaignWebResource {

    @Autowired
    private DiscountService discountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ItemResponse> calculateDiscount(@RequestBody ItemRequest itemRequest) {
        List<DiscountedItem> discountedItems = discountService.calcDiscount(itemRequest.getItems());
        return ResponseEntity.ok(new ItemResponse(discountedItems));
    }
}
