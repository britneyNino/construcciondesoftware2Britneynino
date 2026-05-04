package domain.services;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.abstractmodel.Client;
import domain.ports.out.ClientPort;
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
