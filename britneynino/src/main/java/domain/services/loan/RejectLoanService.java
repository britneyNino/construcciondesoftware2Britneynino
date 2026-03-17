package application.services.loan;

import application.ports.LoanPort;

public class RejectLoanService implements LoanPort {

    @Override
    public void rejectLoan(String loanId) {

        if (loanId == null) {
            throw new IllegalArgumentException("Loan id cannot be null");
        }

        System.out.println("Loan rejected");
    }

    @Override
    public void requestLoan(String clientId, double amount) { }

    @Override
    public void approveLoan(String loanId) { }

    @Override
    public void disburseLoan(String loanId) { }
}