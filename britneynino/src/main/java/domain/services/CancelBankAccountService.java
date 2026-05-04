package domain.services;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.BankAccount;
import domain.model.entity.OperationLog;
import domain.model.enums.AccountStatus;
import domain.model.enums.OperationType;
import domain.model.enums.SystemRole;
import domain.ports.out.BankAccountPort;
import domain.ports.out.OperationLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CancelBankAccountService {

    private final BankAccountPort bankAccountPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public CancelBankAccountService(BankAccountPort bankAccountPort, OperationLogPort operationLogPort) {
        this.bankAccountPort = bankAccountPort;
        this.operationLogPort = operationLogPort;
    }

    public void cancelBankAccount(String accountNumber, String userId, SystemRole userRole)
            throws BusinessException, NotFoundException {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new BusinessException("Account number is required.");
        }
        BankAccount account = bankAccountPort.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found: " + accountNumber);
        }
        // Regla: no se puede cancelar cuenta con saldo
        if (account.getCurrentBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new BusinessException("Cannot cancel an account with remaining balance.");
        }
        account.cancel();
        bankAccountPort.update(account);

        OperationLog log = new OperationLog(OperationType.ACCOUNT_CANCELLED, userId, userRole, accountNumber);
        log.addDetail("accountNumber", accountNumber);
        log.addDetail("newStatus", AccountStatus.CANCELLED.name());
        operationLogPort.append(log);
    }
}
