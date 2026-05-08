package app.infrastructure.jpa.repositories;

import app.domain.model.entity.OperationLog;
import app.domain.model.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, String> {
	List<OperationLog> findByAffectedProductId(String affectedProductId);
	List<OperationLog> findByUserId(String userId);
	List<OperationLog> findByOperationType(OperationType operationType);
}
