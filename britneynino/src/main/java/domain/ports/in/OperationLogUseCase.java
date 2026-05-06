package domain.ports.in;

import domain.exceptions.BusinessException;
import domain.model.entity.OperationLog;
import domain.model.enums.OperationType;
import java.util.List;

public interface OperationLogUseCase {
    void registerOperation(OperationLog operationLog) throws BusinessException;
    List<OperationLog> findByProductId(String productId);
    List<OperationLog> findByUserId(String userId);
    List<OperationLog> findByOperationType(OperationType operationType);
}
