package com.assigment.campaign.repository;

import com.assigment.campaign.domain.Campaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CampaignRepository<T extends Campaign> extends CrudRepository<T,Long> {
}
