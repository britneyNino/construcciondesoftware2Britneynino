package app.domain.ports.in;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.abstractmodel.Client;
import java.util.List;

public interface ClientUseCase {
	void createClient(Client client) throws BusinessException;
	void updateClient(Client client) throws BusinessException, NotFoundException;
	Client findByIdentification(String identification) throws NotFoundException;
	List<Client> findAll();
}
