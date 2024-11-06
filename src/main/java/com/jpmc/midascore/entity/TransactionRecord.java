package com.jpmc.midascore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionRecord {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	 	@Column(nullable = false)
	    private float amount;
	 	
	 	private float incentiveAmount;
	 	

		@ManyToOne
	    private UserRecord sender;
	    
	    @ManyToOne
	    private UserRecord recipient;

	    

	    public TransactionRecord(float amount, float incentiveAmount, UserRecord sender,
				UserRecord recipient) {
			super();
			
			this.amount = amount;
			this.incentiveAmount = incentiveAmount;
			this.sender = sender;
			this.recipient = recipient;
		}

		public float getIncentiveAmount() {
			return incentiveAmount;
		}

		public void setIncentiveAmount(float incentiveAmount) {
			this.incentiveAmount = incentiveAmount;
		}

		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public UserRecord getSender() {
			return sender;
		}

		public void setSender(UserRecord sender) {
			this.sender = sender;
		}

		public UserRecord getRecipient() {
			return recipient;
		}

		public void setRecipient(UserRecord recipient) {
			this.recipient = recipient;
		}

		@Override
		public String toString() {
			return "TransactionRecord [id=" + id + ", amount=" + amount + ", sender=" + sender + ", recipient="
					+ recipient + "]";
		}
	    
	    
	    
	   
}
