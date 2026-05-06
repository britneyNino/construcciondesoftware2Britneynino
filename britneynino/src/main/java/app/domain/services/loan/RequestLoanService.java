package application.services.loan;

import application.ports.LoanPort;

public class RequestLoanService implements LoanPort {

    @Override
    public void requestLoan(String clientId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid loan amount");
        }

        System.out.println("Loan requested");
    }

    @Override
    public void approveLoan(String loanId) { }

    @Override
    public void rejectLoan(String loanId) { }

    @Override
    public void disburseLoan(String loanId) { }
}