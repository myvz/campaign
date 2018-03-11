package com.assigment.campaign.repository;

import com.assigment.campaign.domain.ProductCampaign;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;


public interface ProductCampaignRepository extends CampaignRepository<ProductCampaign> {

    @Cacheable("campaign")
    Optional<ProductCampaign> findByProductId(Long productId);
}
