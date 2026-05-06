package application.services.client;

import application.ports.ClientPort;

public class UpdateClientService implements ClientPort {

    @Override
    public void updateClient(String clientId, String name) {

        if (clientId == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }

        System.out.println("Client updated");
    }

    @Override
    public void registerClient(String name, String document) { }

    @Override
    public void getClient(String clientId) { }
}