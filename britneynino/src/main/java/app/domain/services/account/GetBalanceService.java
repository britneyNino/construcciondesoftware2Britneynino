package application.services.account;

import application.ports.BankAccountPort;

public class GetBalanceService implements BankAccountPort {

    @Override
    public double getBalance(String accountId) {

        if (accountId == null) {
            throw new IllegalArgumentException("Account id cannot be null");
        }

        System.out.println("Getting account balance");

        return 0;
    }

    @Override
    public void createBankAccount(String clientId) { }

    @Override
    public void blockBankAccount(String accountId) { }

    @Override
    public void cancelBankAccount(String accountId) { }
}