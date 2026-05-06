package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.BankAccount;
import app.domain.model.entity.OperationLog;
import app.domain.model.enums.AccountStatus;
import app.domain.model.enums.OperationType;
import app.domain.ports.out.BankAccountPort;
import app.domain.ports.out.OperationLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockBankAccountService {

    private final BankAccountPort bankAccountPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public BlockBankAccountService(BankAccountPort bankAccountPort, OperationLogPort operationLogPort) {
        this.bankAccountPort = bankAccountPort;
        this.operationLogPort = operationLogPort;
    }

    public void blockBankAccount(String accountNumber, String userId, domain.model.enums.SystemRole userRole)
            throws BusinessException, NotFoundException {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new BusinessException("Account number is required.");
        }
        BankAccount account = bankAccountPort.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("Account not found: " + accountNumber);
        }
        if (account.getStatus() == AccountStatus.BLOCKED) {
            throw new BusinessException("Account is already blocked.");
        }
        if (account.getStatus() == AccountStatus.CANCELLED) {
            throw new BusinessException("Cannot block a cancelled account.");
        }
        account.block();
        bankAccountPort.update(account);

        // Regla enunciado: registrar en bitacora
        OperationLog log = new OperationLog(OperationType.ACCOUNT_BLOCKED, userId, userRole, accountNumber);
        log.addDetail("accountNumber", accountNumber);
        log.addDetail("newStatus", AccountStatus.BLOCKED.name());
        operationLogPort.append(log);
    }
}
