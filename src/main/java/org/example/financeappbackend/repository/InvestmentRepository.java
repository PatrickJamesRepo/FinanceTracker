// src/main/java/org/example/financeappbackend/repository/InvestmentRepository.java

package org.example.financeappbackend.repository;

import org.example.financeappbackend.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    // Additional query methods (if needed) can be defined here
}
