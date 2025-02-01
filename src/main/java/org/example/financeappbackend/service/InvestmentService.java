package org.example.financeappbackend.service;

import org.example.financeappbackend.entity.Investment;
import org.example.financeappbackend.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    public Optional<Investment> getInvestmentById(Long id) {
        return investmentRepository.findById(id);
    }

    public Investment createInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    public Optional<Investment> updateInvestment(Long id, Investment investmentDetails) {
        return investmentRepository.findById(id).map(investment -> {
            investment.setName(investmentDetails.getName());
            investment.setAmount(investmentDetails.getAmount());
            investment.setType(investmentDetails.getType());
            return investmentRepository.save(investment);
        });
    }

    public boolean deleteInvestment(Long id) {
        return investmentRepository.findById(id).map(investment -> {
            investmentRepository.delete(investment);
            return true;
        }).orElse(false);
    }
}
