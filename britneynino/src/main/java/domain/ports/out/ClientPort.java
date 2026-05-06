package domain.ports.out;

import domain.model.abstractmodel.Client;
import java.util.List;

public interface ClientPort {
    boolean existsByIdentification(String identification);
    void save(Client client);
    void update(Client client);
    Client findByIdentification(String identification);
    List<Client> findAll();
}
