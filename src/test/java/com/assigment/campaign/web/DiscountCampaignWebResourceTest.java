package com.assigment.campaign.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DiscountCampaignWebResourceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void calculateDiscount() {
        String requestJson = "{\"items\":[{\"productId\":10,\"categoryId\":200,\"price\":100.99},{\"productId\":5,\"categoryId\":100,\"price\":200},{\"productId\":20,\"categoryId\":100,\"price\":500}]}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(requestJson, headers);
        ResponseEntity<ItemResponse> responseEntityResponseEntity = testRestTemplate.postForEntity("/calculateDiscounts", entity, ItemResponse.class);
        assertEquals(responseEntityResponseEntity.getStatusCode(), HttpStatus.OK);
    }
}