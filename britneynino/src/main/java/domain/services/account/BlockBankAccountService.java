package application.services.account;

import application.ports.BankAccountPort;

public class BlockBankAccountService implements BankAccountPort {

    @Override
    public void blockBankAccount(String accountId) {

        if(accountId == null){
            throw new IllegalArgumentException("Account id required");
        }

        System.out.println("Account blocked");
    }

    @Override
    public void createBankAccount(String clientId) {}

    @Override
    public void cancelBankAccount(String accountId) {}

    @Override
    public double getBalance(String accountId) { return 0; }

}