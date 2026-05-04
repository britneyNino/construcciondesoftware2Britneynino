package domain.ports.out;

import domain.model.entity.OperationLog;
import domain.model.enums.OperationType;
import java.util.List;

// Puerto hacia base de datos NoSQL (bitacora inmutable)
public interface OperationLogPort {
    void append(OperationLog operationLog);
    List<OperationLog> findByProductId(String productId);
    List<OperationLog> findByUserId(String userId);
    List<OperationLog> findByOperationType(OperationType operationType);
}
