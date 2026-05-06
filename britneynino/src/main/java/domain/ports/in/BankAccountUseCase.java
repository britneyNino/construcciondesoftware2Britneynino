package domain.ports.in;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.BankAccount;
import java.math.BigDecimal;
import java.util.List;

public interface BankAccountUseCase {
    void openBankAccount(BankAccount bankAccount) throws BusinessException;
    void blockBankAccount(String accountNumber) throws BusinessException, NotFoundException;
    void cancelBankAccount(String accountNumber) throws BusinessException, NotFoundException;
    BigDecimal getBalance(String accountNumber) throws NotFoundException;
    BankAccount findByAccountNumber(String accountNumber) throws NotFoundException;
    List<BankAccount> findByClientIdentification(String identification);
}
