package com.greychain.controller;

import com.greychain.exception.InValidLoanException;
import com.greychain.model.Loan;
import com.greychain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {
    @Autowired
    LoanService loanService;
    @PostMapping("/loan")
     public void createLoan(@RequestBody Loan loan) throws InValidLoanException {
        loanService.saveLoan(loan);
    }


}
