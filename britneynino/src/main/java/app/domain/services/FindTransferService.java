package app.domain.services;

import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.Transfer;
import app.domain.ports.out.TransferPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTransferService {

	private final TransferPort transferPort;

	@Autowired
	public FindTransferService(TransferPort transferPort) {
		this.transferPort = transferPort;
	}

	public Transfer findById(String transferId) throws NotFoundException {
		Transfer transfer = transferPort.findById(transferId);
		if (transfer == null) {
			throw new NotFoundException("Transfer not found: " + transferId);
		}
		return transfer;
	}

	public List<Transfer> findByClientIdentification(String identification) {
		return transferPort.findByClientIdentification(identification);
	}
}
