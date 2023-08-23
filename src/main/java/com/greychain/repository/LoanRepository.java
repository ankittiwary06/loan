package com.greychain.repository;

import com.greychain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    @Query(
            value = "SELECT sum(remainingAmount),customerId,lenderId,interest FROM Loan GROUP BY customerId,lenderId,interest ",
            nativeQuery = true)
     List<Loan> aggregation();
}
