package com.assigment.campaign.web;

import com.assigment.campaign.domain.Campaign;
import com.assigment.campaign.domain.Product;
import com.assigment.campaign.domain.ProductCampaign;
import com.assigment.campaign.domain.ProductCampaignBuilder;
import com.assigment.campaign.repository.ProductCampaignRepository;
import com.assigment.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/product-campaigns")
public class ProductCampaignRestWebResource {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ProductCampaignRepository repository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity postProductCampaign(@RequestBody ProductCampaignDTO productCampaignDTO) {
        ProductCampaign campaign = new ProductCampaignBuilder()
                .name(productCampaignDTO.getName())
                .product(new Product(productCampaignDTO.getProductId()))
                .discount(productCampaignDTO.getDiscount())
                .discountType(productCampaignDTO.getDiscountType())
                .maxDiscountAmount(productCampaignDTO.getMaxDiscountAmount())
                .build();
        Campaign saved = campaignService.save(campaign);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity putProductCampaign(@RequestBody ProductCampaignDTO productCampaignDTO, @PathVariable Long id) {
        Optional<ProductCampaign> byId = repository.findById(id);
        return byId.map(productCampaign -> {
            productCampaign.setName(productCampaignDTO.getName());
            productCampaign.setMaxDiscountAmount(productCampaignDTO.getMaxDiscountAmount());
            productCampaign.setDiscountType(productCampaignDTO.getDiscountType());
            productCampaign.setDiscount(productCampaignDTO.getDiscount());
            productCampaign.setProduct(new Product(productCampaign.getProductId()));
            campaignService.save(productCampaign);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getProductCampaign(@PathVariable Long id) {
        Optional<ProductCampaign> byId = repository.findById(id);
        return byId.map(productCampaign ->
                ResponseEntity.ok().body(ProductCampaignDTO.mapFrom(productCampaign)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProductCampaign(@PathVariable Long id) {
        Optional<ProductCampaign> byId = repository.findById(id);
        return byId.map(productCampaign -> {
            campaignService.delete(productCampaign);
            return ResponseEntity.status(204).build();})
                .orElse(ResponseEntity.notFound().build());
    }
}
