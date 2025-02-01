// src/main/java/com/example/financeapp/repository/TransactionRepository.java

package org.example.financeappbackend.repository;

import org.example.financeappbackend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCategoryId(Long categoryId);
    List<Transaction> findByIsRecurringTrue();
}
