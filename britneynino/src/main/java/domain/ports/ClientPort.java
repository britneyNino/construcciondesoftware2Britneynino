package application.ports;

public interface ClientPort {

    void registerClient(String name, String document);

    void updateClient(String clientId, String name);

    void getClient(String clientId);

}