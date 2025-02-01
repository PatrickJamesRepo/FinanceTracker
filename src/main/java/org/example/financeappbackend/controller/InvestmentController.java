package org.example.financeappbackend.controller;

import org.example.financeappbackend.entity.Investment;
import org.example.financeappbackend.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    // Get all investments
    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.getAllInvestments();
    }

    // Get investment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        Optional<Investment> investment = investmentService.getInvestmentById(id);
        return investment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new investment
    @PostMapping
    public ResponseEntity<Investment> createInvestment(@Valid @RequestBody Investment investment) {
        Investment createdInvestment = investmentService.createInvestment(investment);
        return ResponseEntity.ok(createdInvestment);
    }

    // Update an investment
    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @Valid @RequestBody Investment investmentDetails) {
        Optional<Investment> updatedInvestment = investmentService.updateInvestment(id, investmentDetails);
        return updatedInvestment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an investment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        boolean deleted = investmentService.deleteInvestment(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
