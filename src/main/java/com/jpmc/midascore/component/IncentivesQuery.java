package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class IncentivesQuery {
	 @Value("${incentives.api.url:http://localhost:8080/incentive}")
	    private String incentiveApiUrl;
	    private final RestTemplate restTemplate;
	    
	    public IncentivesQuery(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    public Incentive getIncentive(Transaction transaction) {
	        return restTemplate.postForObject(incentiveApiUrl, transaction, Incentive.class);
	    }
	

}
