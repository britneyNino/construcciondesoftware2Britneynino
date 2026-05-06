package domain.services;

import domain.exceptions.NotFoundException;
import domain.model.entity.BankAccount;
import domain.ports.out.BankAccountPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FindBankAccountService {

    private final BankAccountPort bankAccountPort;

    @Autowired
    public FindBankAccountService(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public BankAccount findByAccountNumber(String accountNumber) throws NotFoundException {
        BankAccount account = bankAccountPort.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found: " + accountNumber);
        }
        return account;
    }

    public BigDecimal getBalance(String accountNumber) throws NotFoundException {
        return findByAccountNumber(accountNumber).getCurrentBalance();
    }

    public List<BankAccount> findByClientIdentification(String identification) {
        return bankAccountPort.findByClientIdentification(identification);
    }
}
