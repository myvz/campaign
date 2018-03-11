package com.assigment.campaign.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class Entity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version=0L;

    protected Entity() {

    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
