package com.assigment.campaign.repository;

import com.assigment.campaign.domain.ProductCampaign;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;


public interface ProductCampainRepository extends CampaignRepository<ProductCampaign> {

    @Cacheable("productsCampaign")
    Optional<ProductCampaign> findByProduct(Long productId);

}
