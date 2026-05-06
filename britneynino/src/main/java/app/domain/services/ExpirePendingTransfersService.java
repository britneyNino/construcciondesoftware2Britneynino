package domain.services;

import domain.model.entity.OperationLog;
import domain.model.entity.Transfer;
import domain.model.enums.OperationType;
import domain.model.enums.SystemRole;
import domain.ports.out.OperationLogPort;
import domain.ports.out.TransferPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpirePendingTransfersService {

    private final TransferPort transferPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public ExpirePendingTransfersService(TransferPort transferPort, OperationLogPort operationLogPort) {
        this.transferPort = transferPort;
        this.operationLogPort = operationLogPort;
    }

    // Regla enunciado: transferencias en PENDING_APPROVAL por mas de 60 minutos -> VENCIDA
    public void expirePendingTransfers() {
        List<Transfer> expired = transferPort.findPendingApprovalOlderThanMinutes(60);
        for (Transfer transfer : expired) {
            // Regla encapsulada en entidad: solo PENDING_APPROVAL -> EXPIRED
            transfer.expire();
            transferPort.update(transfer);

            // Regla enunciado: registrar motivo de vencimiento en bitacora
            OperationLog log = new OperationLog(OperationType.TRANSFER_EXPIRED, "SYSTEM",
                    SystemRole.INTERNAL_ANALYST, transfer.getTransferId());
            log.addDetail("reason", "No approval received within 60 minutes");
            log.addDetail("createdAt", transfer.getCreatedAt().toString());
            log.addDetail("creatorUserId", transfer.getCreatorUserId());
            operationLogPort.append(log);
        }
    }
}
