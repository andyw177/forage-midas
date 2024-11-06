package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    
   
    
    public DatabaseConduit(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository =  transactionRepository;
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    @Transactional
    public void processTransaction(Transaction transaction,Incentive incentive) {
        UserRecord sender = userRepository.findById(transaction.getSenderId());
        UserRecord recipient = userRepository.findById(transaction.getRecipientId());
        
        // Deduct amount from sender and add to recipient.
        sender.setBalance(sender.getBalance() - transaction.getAmount());
//        recipient.setBalance(recipient.getBalance() + transaction.getAmount());

        // Persist changes and transaction record.
        userRepository.save(sender);

        // Add incentive amount to recipient's balance if valid
        float incentiveAmount = incentive.getAmount();
        recipient.setBalance(recipient.getBalance() + transaction.getAmount() + incentiveAmount);

        // Save updated recipient and transaction record
        userRepository.save(recipient);
        

        TransactionRecord transactionRecord = new TransactionRecord(transaction.getAmount(), incentiveAmount, sender, recipient);
        transactionRepository.save(transactionRecord);
    }
    
    public Balance getUserBalance(int id) {
    	Balance bal; 
        UserRecord user = userRepository.findById(id);

        if(user != null) {
        	bal = new Balance(user.getBalance());
        }else {
        	bal = new Balance(0);
        }
 
        return bal;
    }
    
    public boolean isValid(Transaction transaction) {
    	   UserRecord sender = userRepository.findById(transaction.getSenderId());
           UserRecord recipient = userRepository.findById(transaction.getRecipientId());

           if (sender == null || recipient == null || sender.getBalance() < transaction.getAmount()) {
               // Transaction is invalid; discard without any modifications.
               return false;
           }
           return true;
    }
}
