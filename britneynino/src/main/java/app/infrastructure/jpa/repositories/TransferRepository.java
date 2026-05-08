package app.infrastructure.jpa.repositories;

import app.domain.model.entity.Transfer;
import app.domain.model.enums.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {
	List<Transfer> findBySourceAccount_Client_IdentificationOrDestinationAccount_Client_Identification(
			String sourceIdentification, String destinationIdentification);
	List<Transfer> findByStatusAndCreatedAtBefore(TransferStatus status, LocalDateTime before);
}
