package application.services.loan;

import application.ports.LoanPort;

public class DisburseLoanService  {

    @Override
    public void disburseLoan(String loanId) {

        if (loanId == null) {
            throw new IllegalArgumentException("Loan id cannot be null");
        }

        System.out.println("Loan disbursed");
    }

}