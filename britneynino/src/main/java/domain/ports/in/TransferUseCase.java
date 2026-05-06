package domain.ports.in;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.Transfer;
import java.util.List;

public interface TransferUseCase {
    void createTransfer(Transfer transfer) throws BusinessException;
    void approveTransfer(String transferId, String supervisorUserId) throws BusinessException, NotFoundException;
    void rejectTransfer(String transferId, String supervisorUserId) throws BusinessException, NotFoundException;
    void executeTransfer(String transferId) throws BusinessException, NotFoundException;
    void expirePendingTransfers() throws BusinessException;
    Transfer findById(String transferId) throws NotFoundException;
    List<Transfer> findByClientIdentification(String identification);
}
