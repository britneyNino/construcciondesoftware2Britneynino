package application.services.account;

import application.ports.BankAccountPort;

public class CancelBankAccountService implements BankAccountPort {

    @Override
    public void cancelBankAccount(String accountId) {

        if (accountId == null) {
            throw new IllegalArgumentException("Account id cannot be null");
        }

        System.out.println("Bank account cancelled");
    }

    @Override
    public void createBankAccount(String clientId) { }

    @Override
    public void blockBankAccount(String accountId) { }

    @Override
    public double getBalance(String accountId) {
        return 0;
    }
}