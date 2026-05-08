package app.infrastructure.jpa.adapters;

import app.domain.model.entity.Transfer;
import app.domain.ports.out.TransferPort;
import app.infrastructure.jpa.repositories.TransferRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import app.domain.model.enums.TransferStatus;

@Component
public class JpaTransferPortAdapter implements TransferPort {

	private final TransferRepository transferRepository;

	public JpaTransferPortAdapter(TransferRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

	@Override
	@Transactional
	public void save(Transfer transfer) {
		if (transfer == null) return;
		transferRepository.save(transfer);
	}

	@Override
	@Transactional
	public void update(Transfer transfer) {
		if (transfer == null) return;
		transferRepository.save(transfer);
	}

	@Override
	public Transfer findById(String transferId) {
		if (transferId == null) return null;
		return transferRepository.findById(transferId).orElse(null);
	}

	@Override
	public List<Transfer> findByClientIdentification(String identification) {
		if (identification == null) return List.of();
		return transferRepository.findBySourceAccount_Client_IdentificationOrDestinationAccount_Client_Identification(
				identification, identification);
	}

	@Override
	public List<Transfer> findPendingApprovalOlderThanMinutes(int minutes) {
		if (minutes <= 0) return List.of();
		LocalDateTime threshold = LocalDateTime.now().minusMinutes(minutes);
		return transferRepository.findByStatusAndCreatedAtBefore(TransferStatus.PENDING_APPROVAL, threshold);
	}
}
