package com.assigment.campaign.domain;

@javax.persistence.Entity
public class Category extends Entity {

    private String name;

    Category() {

    }

    public Category(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
