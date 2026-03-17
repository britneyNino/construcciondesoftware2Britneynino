package application.services.loan;

import application.ports.LoanPort;

public class DisburseLoanService implements LoanPort {

    @Override
    public void disburseLoan(String loanId) {

        if (loanId == null) {
            throw new IllegalArgumentException("Loan id cannot be null");
        }

        System.out.println("Loan disbursed");
    }

    @Override
    public void requestLoan(String clientId, double amount) { }

    @Override
    public void approveLoan(String loanId) { }

    @Override
    public void rejectLoan(String loanId) { }
}