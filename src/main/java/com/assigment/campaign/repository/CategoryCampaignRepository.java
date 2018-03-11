package com.assigment.campaign.repository;

import com.assigment.campaign.domain.CategoryCampaign;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public interface CategoryCampaignRepository extends CampaignRepository<CategoryCampaign> {

    @Cacheable("campaign")
    Optional<CategoryCampaign> findByCategoryId(Long categoryId);
}
