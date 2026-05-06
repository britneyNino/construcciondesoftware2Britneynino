package application.ports;

public interface LoanPort {

    void requestLoan(String clientId, double amount);

    void approveLoan(String loanId);

    void rejectLoan(String loanId);

    void disburseLoan(String loanId);

}