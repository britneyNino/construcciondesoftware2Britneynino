package application.ports;

public interface BankAccountPort {

    void createBankAccount(String clientId);

    void blockBankAccount(String accountId);

    void cancelBankAccount(String accountId);

    double getBalance(String accountId);

}
