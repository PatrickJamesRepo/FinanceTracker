// src/main/java/org/example/financeappbackend/service/BudgetService.java

package org.example.financeappbackend.service;

import org.example.financeappbackend.entity.Budget;
import org.example.financeappbackend.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public Optional<Budget> getBudgetByCategoryIdAndMonth(Long categoryId, Month month) {
        return budgetRepository.findByCategoryIdAndMonth(categoryId, month);
    }

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Optional<Budget> updateBudget(Long id, Budget budgetDetails) {
        return budgetRepository.findById(id).map(budget -> {
            budget.setAmount(budgetDetails.getAmount());
            budget.setMonth(budgetDetails.getMonth());
            budget.setCategory(budgetDetails.getCategory());
            return budgetRepository.save(budget);
        });
    }

    public boolean deleteBudget(Long id) {
        return budgetRepository.findById(id).map(budget -> {
            budgetRepository.delete(budget);
            return true;
        }).orElse(false);
    }
}
