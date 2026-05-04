package domain.services;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.OperationLog;
import domain.model.entity.Transfer;
import domain.model.enums.OperationType;
import domain.model.enums.SystemRole;
import domain.ports.out.OperationLogPort;
import domain.ports.out.TransferPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectTransferService {

    private final TransferPort transferPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public RejectTransferService(TransferPort transferPort, OperationLogPort operationLogPort) {
        this.transferPort = transferPort;
        this.operationLogPort = operationLogPort;
    }

    // Regla enunciado: solo Supervisor de Empresa puede rechazar
    public void rejectTransfer(String transferId, String supervisorUserId)
            throws BusinessException, NotFoundException {
        if (transferId == null || transferId.isBlank()) {
            throw new BusinessException("Transfer id is required.");
        }
        Transfer transfer = transferPort.findById(transferId);
        if (transfer == null) {
            throw new NotFoundException("Transfer not found: " + transferId);
        }
        // Regla encapsulada en entidad: solo PENDING_APPROVAL -> REJECTED
        transfer.reject(supervisorUserId);
        transferPort.update(transfer);

        OperationLog log = new OperationLog(OperationType.TRANSFER_REJECTED, supervisorUserId,
                SystemRole.COMPANY_SUPERVISOR, transferId);
        log.addDetail("supervisorUserId", supervisorUserId);
        operationLogPort.append(log);
    }
}
