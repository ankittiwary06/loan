package com.greychain.repository;

import com.greychain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
    @Query(
            value = "SELECT sum(remaining_amount),customer_id,lender_id,interest FROM Loan GROUP BY customer_id,lender_id,interest",
            nativeQuery = true)
     List<Object> remainingAmountTotalByCustomerLenderInterest();
}
