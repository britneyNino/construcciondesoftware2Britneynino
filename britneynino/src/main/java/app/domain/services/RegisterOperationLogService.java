package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.model.entity.OperationLog;
import app.domain.model.enums.OperationType;
import app.domain.ports.out.OperationLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RegisterOperationLogService {

    private final OperationLogPort operationLogPort;

    @Autowired
    public RegisterOperationLogService(OperationLogPort operationLogPort) {
        this.operationLogPort = operationLogPort;
    }

    // Registro inmutable: id y timestamp siempre generados aqui
    public void registerOperation(OperationLog operationLog) throws BusinessException {
        if (operationLog.getOperationType() == null) {
            throw new BusinessException("Operation type is required.");
        }
        if (operationLog.getUserId() == null || operationLog.getUserId().isBlank()) {
            throw new BusinessException("User id is required for traceability.");
        }
        if (operationLog.getAffectedProductId() == null || operationLog.getAffectedProductId().isBlank()) {
            throw new BusinessException("Affected product id is required.");
        }
        operationLog.setLogId(UUID.randomUUID().toString());
        operationLog.setTimestamp(LocalDateTime.now());
        operationLogPort.append(operationLog);
    }

    public List<OperationLog> findByProductId(String productId) {
        return operationLogPort.findByProductId(productId);
    }

    public List<OperationLog> findByUserId(String userId) {
        return operationLogPort.findByUserId(userId);
    }

    public List<OperationLog> findByOperationType(OperationType operationType) {
        return operationLogPort.findByOperationType(operationType);
    }
}
