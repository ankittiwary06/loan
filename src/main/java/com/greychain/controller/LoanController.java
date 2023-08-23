package com.greychain.controller;

import com.greychain.exception.InvalidLoanException;
import com.greychain.model.Loan;
import com.greychain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {
    @Autowired
    LoanService loanService;
    @PostMapping("/loan")
     public void createLoan(@RequestBody Loan loan) throws InvalidLoanException {
        loanService.saveLoan(loan);
    }
    @GetMapping("loan/aggregate")
    public List<Object> get()
    {
     return loanService.getAggregateRemainingAmount();
    }
    @GetMapping("loan/Due")
    public void logDue(){
        loanService.checkLoanDueDate();
    }
}
