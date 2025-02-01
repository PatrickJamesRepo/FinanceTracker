package org.example.financeappbackend.repository;

import org.example.financeappbackend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Month;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByCategoryIdAndMonth(Long categoryId, Month month);
}
