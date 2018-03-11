package com.assigment.campaign.repository;

import com.assigment.campaign.domain.Campaign;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface CampaignRepository<T extends Campaign> extends CrudRepository<T,Long> {

    @Override
    <S extends T> S save(S s);

}
