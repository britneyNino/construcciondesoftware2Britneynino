package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.OperationLog;
import app.domain.model.entity.Transfer;
import app.domain.model.enums.OperationType;
import app.domain.model.enums.SystemRole;
import app.domain.ports.out.OperationLogPort;
import app.domain.ports.out.TransferPort;
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
	public void rejectTransfer(String transferId, String supervisorUserId) throws BusinessException, NotFoundException {
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
