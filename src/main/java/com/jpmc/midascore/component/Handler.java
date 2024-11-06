package com.jpmc.midascore.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;

@Component
public class Handler {
    static final Logger logger = LoggerFactory.getLogger(Handler.class);
    private final DatabaseConduit databaseConduit;
    private final IncentivesQuery incentiveQuerier;

    public Handler(DatabaseConduit databaseConduit, IncentivesQuery incentiveQuerier) {
        this.databaseConduit = databaseConduit;
		this.incentiveQuerier = incentiveQuerier;
    }

	public void processTransaction(Transaction transaction) {
		 if (databaseConduit.isValid(transaction)) {
	            Incentive incentive = incentiveQuerier.getIncentive(transaction);
	            databaseConduit.processTransaction(transaction, incentive);
	        }
		
	}
}
