package domain.services;

import domain.exceptions.NotFoundException;
import domain.model.abstractmodel.Client;
import domain.ports.out.ClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindClientService {

    private final ClientPort clientPort;

    @Autowired
    public FindClientService(ClientPort clientPort) {
        this.clientPort = clientPort;
    }

    public Client findByIdentification(String identification) throws NotFoundException {
        Client client = clientPort.findByIdentification(identification);
        if (client == null) {
            throw new NotFoundException("Client not found: " + identification);
        }
        return client;
    }

    public List<Client> findAll() {
        return clientPort.findAll();
    }
}
