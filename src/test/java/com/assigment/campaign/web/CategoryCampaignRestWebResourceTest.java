package com.assigment.campaign.web;


import com.assigment.campaign.domain.DiscountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.net.URI;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CategoryCampaignRestWebResourceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private URI createdLocation;

    @Test
    public void testPost() {
        CategoryCampaignDTO categoryCampaignDTO=new CategoryCampaignDTO();
        categoryCampaignDTO.setCategoryId(100L);
        categoryCampaignDTO.setDiscountType(DiscountType.RATE);
        categoryCampaignDTO.setMaxDiscountAmount(BigDecimal.valueOf(10));
        categoryCampaignDTO.setName("Kazaklar Daha Ucuz");
        categoryCampaignDTO.setDiscount(BigDecimal.valueOf(100));
        ResponseEntity responseEntity = testRestTemplate.postForEntity("/category-campaigns", categoryCampaignDTO, Void.class);
        this.createdLocation=responseEntity.getHeaders().getLocation();
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }

    @Test
    public void testPut() {
        testPost();
        CategoryCampaignDTO categoryCampaignDTO=new CategoryCampaignDTO();
        categoryCampaignDTO.setCategoryId(100L);
        categoryCampaignDTO.setDiscountType(DiscountType.RATE);
        categoryCampaignDTO.setMaxDiscountAmount(BigDecimal.valueOf(10));
        categoryCampaignDTO.setName("Kazaklar Daha Ucuz");
        categoryCampaignDTO.setDiscount(BigDecimal.valueOf(200));
        HttpEntity httpEntity = new HttpEntity(categoryCampaignDTO);
        ResponseEntity<Void> exchange = testRestTemplate.exchange(createdLocation, HttpMethod.PUT, httpEntity, Void.class);
        assertEquals(HttpStatus.OK,exchange.getStatusCode());
    }

    @Test
    public void testDelete() {
        testPost();
        ResponseEntity<Void> exchange = testRestTemplate.exchange(createdLocation, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.valueOf(204),exchange.getStatusCode());
    }

    @Test
    public void testGet() {
        testPost();
        ResponseEntity forEntity = testRestTemplate.getForEntity(createdLocation, Void.class);
        assertEquals(HttpStatus.OK,forEntity.getStatusCode());
    }

}