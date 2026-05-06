package application.services.account;

import application.ports.BankAccountPort;

public class CreateBankAccountService implements BankAccountPort {

    @Override
    public void createBankAccount(String clientId) {

        if(clientId == null){
            throw new IllegalArgumentException("Client id cannot be null");
        }

        System.out.println("Account created");
    }

    @Override
    public void blockBankAccount(String accountId) {}

    @Override
    public void cancelBankAccount(String accountId) {}

    @Override
    public double getBalance(String accountId) { return 0; }

}