package com.assigment.campaign.service.impl;

import com.assigment.campaign.domain.Campaign;
import com.assigment.campaign.repository.CampaignRepository;
import com.assigment.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CampaignServiceImpl implements CampaignService {


    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <T extends Campaign> Campaign save(T campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Campaign campaign) {
        campaignRepository.delete(campaign);
    }
}
