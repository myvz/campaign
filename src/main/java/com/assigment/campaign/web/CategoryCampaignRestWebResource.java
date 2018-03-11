package com.assigment.campaign.web;

import com.assigment.campaign.domain.Campaign;
import com.assigment.campaign.domain.Category;
import com.assigment.campaign.domain.CategoryCampaign;
import com.assigment.campaign.domain.CategoryCampaignBuilder;
import com.assigment.campaign.repository.CategoryCampaignRepository;
import com.assigment.campaign.service.CampaignService;
import com.assigment.campaign.web.CategoryCampaignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/category-campaigns")
public class CategoryCampaignRestWebResource {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CategoryCampaignRepository repository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity post(@RequestBody CategoryCampaignDTO categoryCampaignDTO) {
        CategoryCampaign campaign = new CategoryCampaignBuilder()
                .name(categoryCampaignDTO.getName())
                .category(new Category(categoryCampaignDTO.getCategoryId()))
                .discount(categoryCampaignDTO.getDiscount())
                .discountType(categoryCampaignDTO.getDiscountType())
                .maxDiscountAmount(categoryCampaignDTO.getMaxDiscountAmount())
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
    public ResponseEntity put(@RequestBody CategoryCampaignDTO categoryCampaignDTO, @PathVariable Long id) {
        Optional<CategoryCampaign> byId = repository.findById(id);
        return byId.map(categoryCampaign -> {
            categoryCampaign.setName(categoryCampaignDTO.getName());
            categoryCampaign.setMaxDiscountAmount(categoryCampaignDTO.getMaxDiscountAmount());
            categoryCampaign.setDiscountType(categoryCampaignDTO.getDiscountType());
            categoryCampaign.setDiscount(categoryCampaignDTO.getDiscount());
            categoryCampaign.setCategory(new Category(categoryCampaignDTO.getCategoryId()));
            campaignService.save(categoryCampaign);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Optional<CategoryCampaign> byId = repository.findById(id);
        return byId.map(categoryCampaign ->
                ResponseEntity.ok().body(CategoryCampaignDTO.mapFrom(categoryCampaign)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<CategoryCampaign> byId = repository.findById(id);
        return byId.map(categoryCampaign -> {
                campaignService.delete(categoryCampaign);
                return ResponseEntity.status(204).build();})
                .orElse(ResponseEntity.notFound().build());
    }
}
