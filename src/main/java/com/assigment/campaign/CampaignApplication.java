package com.assigment.campaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CampaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampaignApplication.class, args);
	}
}
