package com.greychain.service;
import com.greychain.exception.InValidLoanException;
import com.greychain.model.Loan;
import com.greychain.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class LoanService {
    @Autowired
    LoanRepository loanRepository;
    Logger logger = LoggerFactory.getLogger(LoanService.class);

    public void validateLoan(Loan loan) throws InValidLoanException {
        if (loan.getPaymentDate().after(loan.getDueDate())) {
            {
                logger.error("Invalid loan Exception");
                throw new InValidLoanException();
            }

        }
    }

    public  void saveLoan(Loan loan) throws InValidLoanException {
        try {
            validateLoan(loan);
            loanRepository.save(loan);
        } catch (InValidLoanException e) {
            logger.error(e.getMessage());
            throw e;

        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw e;
        }

    }

    public void checkLoanDueDate() {

        List<Loan> loans=loanRepository.findAll();
        Date todayDate=new Date();
        for (Loan item : loans) {


            if(item.getDueDate().before(new Date()))
            {
                logger.warn("alert for loan id " + item.getId() + " Due date =" + item.getDueDate());
            }
        }

    }

    public  void getAggregateRemainingAmount() {
        loanRepository.aggregation();
    }




}