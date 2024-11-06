package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.foundation.Transaction;

import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaConsumer {

	 @Value("${general.kafka-topic}")
	    private String topic;
	 	private final Handler handler;
	 	public KafkaConsumer(Handler handler) {
	        this.handler = handler;
	    }
	    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core-group")
	    public void listen(Transaction transaction) {
//    	 System.out.println(transaction);
	    	handler.processTransaction(transaction);
    }

  
}
 