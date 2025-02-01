// src/main/java/org/example/financeappbackend/controller/TransactionController.java

package org.example.financeappbackend.controller;

import org.example.financeappbackend.entity.Transaction;
import org.example.financeappbackend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // Get recurring transactions
    @GetMapping("/recurring")
    public List<Transaction> getRecurringTransactions() {
        return transactionService.getRecurringTransactions();
    }

    // Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    // Update a transaction
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @Valid @RequestBody Transaction transactionDetails) {
        Optional<Transaction> updatedTransaction = transactionService.updateTransaction(id, transactionDetails);
        return updatedTransaction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        boolean deleted = transactionService.deleteTransaction(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
