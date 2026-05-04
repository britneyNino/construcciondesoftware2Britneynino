package domain.ports.in;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.abstractmodel.Client;
import java.util.List;

public interface ClientUseCase {
    void createClient(Client client) throws BusinessException;
    void updateClient(Client client) throws BusinessException, NotFoundException;
    Client findByIdentification(String identification) throws NotFoundException;
    List<Client> findAll();
}
