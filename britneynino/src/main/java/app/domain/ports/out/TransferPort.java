package app.domain.ports.out;

import app.domain.model.entity.Transfer;
import java.util.List;

public interface TransferPort {
	void save(Transfer transfer);
	void update(Transfer transfer);
	Transfer findById(String transferId);
	List<Transfer> findByClientIdentification(String identification);
	// Regla enunciado: transferencias en PENDING_APPROVAL por mas de 60 minutos
	List<Transfer> findPendingApprovalOlderThanMinutes(int minutes);
}
