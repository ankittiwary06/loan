package com.greychain.service;


import com.greychain.exception.InvalidLoanException;
import com.greychain.model.Loan;
import com.greychain.repository.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {
    @InjectMocks
    LoanService loanService = new LoanService();
    @Mock
    LoanRepository loanRepository;
    @Mock
    Logger logger;

    @Test
    public void saveLoanSuccess() throws InvalidLoanException {
        Loan loan = new Loan();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            loan.setDueDate(formatter.parse("17-12-2022"));
            loan.setPaymentDate(formatter.parse("16-12-2022"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        loanService.saveLoan(loan);
        Mockito.verify(loanRepository, Mockito.times(1)).save(loan);
    }

    @Test
    public void validateLoanSuccessWithSameDate() throws Exception {
        Loan loan = new Loan();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        loan.setDueDate(formatter.parse("17-11-2022"));
        loan.setPaymentDate(formatter.parse("17-11-2022"));
        loanService.saveLoan(loan);
        Mockito.verify(loanRepository, Mockito.times(1)).save(loan);
    }

    @Test(expected = InvalidLoanException.class)
    public void validateLoanException() throws Exception {
        Loan loan = new Loan();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        loan.setDueDate(formatter.parse("22-04-2023"));
        loan.setPaymentDate(formatter.parse("23-05-2023"));
        loanService.saveLoan(loan);

    }

    @Test
    public void checkDueDateTest() {

        List<Loan> loans = new ArrayList<Loan>();
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            loan1.setDueDate(formatter.parse("17-12-2022"));
            loan1.setPaymentDate(formatter.parse("17-11-2022"));
            loan2.setDueDate(formatter.parse("17-12-2030"));
            loan2.setPaymentDate(formatter.parse("17-11-2022"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        loans.add(loan1);
        loans.add(loan2);
        Mockito.when(loanRepository.findAll()).thenReturn(loans);
        loanService.checkLoanDueDate();
        Mockito.verify(logger, Mockito.times(1)).warn("alert for loan id " + loan1.getId() + " Due date =" + loan1.getDueDate());
    }


}
