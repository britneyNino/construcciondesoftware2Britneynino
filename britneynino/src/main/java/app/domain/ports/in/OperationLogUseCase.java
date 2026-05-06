package app.domain.ports.in;

import app.domain.exceptions.BusinessException;
import app.domain.model.entity.OperationLog;
import app.domain.model.enums.OperationType;
import java.util.List;

public interface OperationLogUseCase {
    void registerOperation(OperationLog operationLog) throws BusinessException;
    List<OperationLog> findByProductId(String productId);
    List<OperationLog> findByUserId(String userId);
    List<OperationLog> findByOperationType(OperationType operationType);
}
