package com.assigment.campaign.service;

import com.assigment.campaign.domain.Campaign;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

public interface CampaignService {

    @CachePut("campaign")
    <T extends Campaign> Campaign save(T campaign);

    @CacheEvict("campaign")
    void delete(Campaign campaign);


}
