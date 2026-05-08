package app.domain.ports.out;

import app.domain.model.entity.OperationLog;
import app.domain.model.enums.OperationType;
import java.util.List;

// Puerto hacia base de datos NoSQL (bitacora inmutable)
public interface OperationLogPort {
	void append(OperationLog operationLog);
	List<OperationLog> findByAffectedProductId(String affectedProductId);
	List<OperationLog> findByUserId(String userId);
	List<OperationLog> findByOperationType(OperationType operationType);
}
