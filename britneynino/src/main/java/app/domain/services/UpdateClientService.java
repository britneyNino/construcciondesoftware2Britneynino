package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.abstractmodel.Client;
import app.domain.ports.out.ClientPort;
import app.domain.model.abstractmodel.Client;
import app.domain.model.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientService {

	private final ClientPort clientPort;

	@Autowired
	public UpdateClientService(ClientPort clientPort) {
		this.clientPort = clientPort;
	}

	public void updateClient(Client client) throws BusinessException, NotFoundException {
		if (client.getIdentification() == null || client.getIdentification().isBlank()) {
			throw new BusinessException("Client identification is required.");
		}
		Client existing = clientPort.findByIdentification(client.getIdentification());
		if (existing == null) {
			throw new NotFoundException("Client not found: " + client.getIdentification());
		}
		if (existing.getUser() == null) {
			throw new BusinessException("Client has no associated system user.");
		}
		if (existing.getUser().getStatus() == UserStatus.INACTIVE || existing.getUser().getStatus() == UserStatus.BLOCKED) {
			throw new BusinessException("The client is not active or is blocked.");
		}
		clientPort.update(client);
	}
}
