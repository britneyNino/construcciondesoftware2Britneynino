package domain.services;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
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

@Service
public class ApproveTransferService {

    private final TransferPort transferPort;
    private final BankAccountPort bankAccountPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public ApproveTransferService(TransferPort transferPort, BankAccountPort bankAccountPort,
                                  OperationLogPort operationLogPort) {
        this.transferPort = transferPort;
        this.bankAccountPort = bankAccountPort;
        this.operationLogPort = operationLogPort;
    }

    // Regla enunciado: solo Supervisor de Empresa puede aprobar transferencias de alto monto
    public void approveTransfer(String transferId, String supervisorUserId)
            throws BusinessException, NotFoundException {
        if (transferId == null || transferId.isBlank()) {
            throw new BusinessException("Transfer id is required.");
        }
        Transfer transfer = transferPort.findById(transferId);
        if (transfer == null) {
            throw new NotFoundException("Transfer not found: " + transferId);
        }
        // Regla: valida saldo suficiente antes de ejecutar
        BankAccount source = transfer.getSourceAccount();
        if (transfer.getAmount().compareTo(source.getCurrentBalance()) > 0) {
            throw new BusinessException("Insufficient balance in source account.");
        }
        // Regla encapsulada en entidad: solo PENDING_APPROVAL -> APPROVED
        transfer.approve(supervisorUserId);
        // Ejecutar la transferencia financiera
        source.withdraw(transfer.getAmount());
        transfer.getDestinationAccount().deposit(transfer.getAmount());
        transfer.execute();

        transferPort.update(transfer);
        bankAccountPort.update(source);
        bankAccountPort.update(transfer.getDestinationAccount());

        OperationLog log = new OperationLog(OperationType.TRANSFER_EXECUTED, supervisorUserId,
                SystemRole.COMPANY_SUPERVISOR, transferId);
        log.addDetail("amount", transfer.getAmount());
        log.addDetail("supervisorUserId", supervisorUserId);
        log.addDetail("sourceAccount", source.getAccountNumber());
        log.addDetail("destinationAccount", transfer.getDestinationAccount().getAccountNumber());
        operationLogPort.append(log);
    }
}
