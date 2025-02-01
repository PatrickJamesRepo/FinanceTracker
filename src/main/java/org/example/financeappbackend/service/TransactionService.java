// src/main/java/org/example/financeappbackend/service/TransactionService.java

package org.example.financeappbackend.service;

import org.example.financeappbackend.entity.Transaction;
import org.example.financeappbackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByCategoryId(Long categoryId) {
        return transactionRepository.findByCategoryId(categoryId);
    }

    public List<Transaction> getRecurringTransactions() {
        return transactionRepository.findByIsRecurringTrue();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> updateTransaction(Long id, Transaction transactionDetails) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setAmount(transactionDetails.getAmount());
            transaction.setDate(transactionDetails.getDate());
            transaction.setDescription(transactionDetails.getDescription());
            transaction.setIsRecurring(transactionDetails.getIsRecurring());
            transaction.setCategory(transactionDetails.getCategory());
            transaction.setTags(transactionDetails.getTags());
            return transactionRepository.save(transaction);
        });
    }

    public boolean deleteTransaction(Long id) {
        return transactionRepository.findById(id).map(transaction -> {
            transactionRepository.delete(transaction);
            return true;
        }).orElse(false);
    }
}
