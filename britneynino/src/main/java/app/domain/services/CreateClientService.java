package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.model.abstractmodel.Client;
import app.domain.model.entity.NaturalPersonClient;
import app.domain.ports.out.ClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class CreateClientService {

    private final ClientPort clientPort;

    @Autowired
    public CreateClientService(ClientPort clientPort) {
        this.clientPort = clientPort;
    }

    public void createClient(Client client) throws BusinessException {
        if (client.getIdentification() == null || client.getIdentification().isBlank()) {
            throw new BusinessException("Client identification is required.");
        }
        if (clientPort.existsByIdentification(client.getIdentification())) {
            throw new BusinessException("A client with that identification already exists.");
        }
        if (client.getEmail() == null || client.getEmail().isBlank() || !client.getEmail().contains("@")) {
            throw new BusinessException("A valid email is required.");
        }
        if (client.getPhone() == null || client.getPhone().length() < 7 || client.getPhone().length() > 15) {
            throw new BusinessException("Phone must be between 7 and 15 digits.");
        }
        if (client.getAddress() == null || client.getAddress().isBlank()) {
            throw new BusinessException("Address is required.");
        }
        // Regla enunciado: persona natural debe ser mayor de edad
        if (client instanceof NaturalPersonClient natural) {
            if (natural.getBirthDate() == null) {
                throw new BusinessException("Birth date is required for natural person clients.");
            }
            int age = Period.between(natural.getBirthDate(), LocalDate.now()).getYears();
            if (age < 18) {
                throw new BusinessException("Natural person client must be at least 18 years old.");
            }
        }
        clientPort.save(client);
    }
}
