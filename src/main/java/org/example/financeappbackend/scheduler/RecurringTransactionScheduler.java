package org.example.financeappbackend.scheduler;

import org.example.financeappbackend.entity.Transaction;
import org.example.financeappbackend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class RecurringTransactionScheduler {

    @Autowired
    private TransactionService transactionService;

    // Scheduled to run at 00:00 on the first day of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void addRecurringTransactions() {
        List<Transaction> recurringTransactions = transactionService.getRecurringTransactions();
        for (Transaction tx : recurringTransactions) {
            Transaction newTx = new Transaction();
            newTx.setAmount(tx.getAmount());
            newTx.setDate(LocalDate.now());
            newTx.setDescription(tx.getDescription());
            newTx.setIsRecurring(true);
            newTx.setCategory(tx.getCategory());
            newTx.setTags(tx.getTags());
            transactionService.createTransaction(newTx);
        }
    }
}
