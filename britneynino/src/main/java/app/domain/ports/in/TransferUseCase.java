package app.domain.ports.in;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.Transfer;
import app.domain.model.enums.SystemRole;
import java.util.List;

public interface TransferUseCase {
	void createTransfer(Transfer transfer, SystemRole creatorRole) throws BusinessException;
	void approveTransfer(String transferId, String supervisorUserId) throws BusinessException, NotFoundException;
	void rejectTransfer(String transferId, String supervisorUserId) throws BusinessException, NotFoundException;
	void executeTransfer(String transferId) throws BusinessException, NotFoundException;
	void expirePendingTransfers() throws BusinessException;
	Transfer findById(String transferId) throws NotFoundException;
	List<Transfer> findByClientIdentification(String identification);
}
