package domain.services;

import domain.exceptions.BusinessException;
import domain.model.entity.BankAccount;
import domain.model.entity.OperationLog;
import domain.model.entity.Transfer;
import domain.model.enums.OperationType;
import domain.model.enums.SystemRole;
import domain.ports.out.BankAccountPort;
import domain.ports.out.OperationLogPort;
import domain.ports.out.TransferPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreateTransferService {

    private final TransferPort transferPort;
    private final BankAccountPort bankAccountPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public CreateTransferService(TransferPort transferPort, BankAccountPort bankAccountPort,
                                 OperationLogPort operationLogPort) {
        this.transferPort = transferPort;
        this.bankAccountPort = bankAccountPort;
        this.operationLogPort = operationLogPort;
    }

    public void createTransfer(Transfer transfer, SystemRole creatorRole) throws BusinessException {
        if (transfer.getAmount() == null || transfer.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Transfer amount must be greater than zero.");
        }
        // Regla enunciado: cuenta origen debe existir y estar activa
        BankAccount source = bankAccountPort.findByAccountNumber(
                transfer.getSourceAccount().getAccountNumber());
        if (source == null) {
            throw new BusinessException("Source account not found.");
        }
        if (!source.isOperable()) {
            throw new BusinessException("Source account is not operable. Status: " + source.getStatus());
        }
        BankAccount destination = bankAccountPort.findByAccountNumber(
                transfer.getDestinationAccount().getAccountNumber());
        if (destination == null) {
            throw new BusinessException("Destination account not found.");
        }
        // Regla: no se puede transferir a la misma cuenta
        if (source.getAccountNumber().equals(destination.getAccountNumber())) {
            throw new BusinessException("Source and destination account cannot be the same.");
        }
        transfer.setSourceAccount(source);
        transfer.setDestinationAccount(destination);

        // Regla enunciado: si supera el umbral empresarial -> PENDING_APPROVAL
        if (transfer.requiresApproval()) {
            transfer.markAsPendingApproval();
            transferPort.save(transfer);
            OperationLog log = new OperationLog(OperationType.TRANSFER_PENDING_APPROVAL,
                    transfer.getCreatorUserId(), creatorRole, transfer.getTransferId());
            log.addDetail("amount", transfer.getAmount());
            log.addDetail("sourceAccount", source.getAccountNumber());
            log.addDetail("destinationAccount", destination.getAccountNumber());
            operationLogPort.append(log);
        } else {
            // Regla enunciado: si no supera el umbral, se ejecuta directamente
            if (transfer.getAmount().compareTo(source.getCurrentBalance()) > 0) {
                throw new BusinessException("Insufficient balance in source account.");
            }
            source.withdraw(transfer.getAmount());
            destination.deposit(transfer.getAmount());
            transfer.execute();
            transferPort.save(transfer);
            bankAccountPort.update(source);
            bankAccountPort.update(destination);
            OperationLog log = new OperationLog(OperationType.TRANSFER_EXECUTED,
                    transfer.getCreatorUserId(), creatorRole, transfer.getTransferId());
            log.addDetail("amount", transfer.getAmount());
            log.addDetail("sourceAccount", source.getAccountNumber());
            log.addDetail("balanceBeforeSource", source.getCurrentBalance().add(transfer.getAmount()));
            log.addDetail("balanceAfterSource", source.getCurrentBalance());
            log.addDetail("destinationAccount", destination.getAccountNumber());
            operationLogPort.append(log);
        }
    }
}
