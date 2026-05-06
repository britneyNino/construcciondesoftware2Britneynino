package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.abstractmodel.Client;
import app.domain.ports.out.ClientPort;
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
        if (!clientPort.existsByIdentification(client.getIdentification())) {
            throw new NotFoundException("Client not found: " + client.getIdentification());
        }
        clientPort.update(client);
    }
}
