package app.infrastructure.jpa.adapters;

import app.domain.model.entity.OperationLog;
import app.domain.model.enums.OperationType;
import app.domain.ports.out.OperationLogPort;
import app.infrastructure.jpa.repositories.OperationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaOperationLogPortAdapter implements OperationLogPort {

	@Autowired
	private OperationLogRepository operationLogRepository;

	@Override
	public void append(OperationLog operationLog) {
		operationLogRepository.save(operationLog);
	}

	@Override
	public List<OperationLog> findByAffectedProductId(String affectedProductId) {
		return operationLogRepository.findByAffectedProductId(affectedProductId);
	}

	@Override
	public List<OperationLog> findByUserId(String userId) {
		return operationLogRepository.findByUserId(userId);
	}

	@Override
	public List<OperationLog> findByOperationType(OperationType operationType) {
		return operationLogRepository.findByOperationType(operationType);
	}
}
