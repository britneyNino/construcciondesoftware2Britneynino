package application.services.client;

import application.ports.ClientPort;

public class RegisterClientService implements ClientPort {

    @Override
    public void registerClient(String name, String document) {

        if (name == null || document == null) {
            throw new IllegalArgumentException("Client information is required");
        }

        System.out.println("Client registered");
    }

    @Override
    public void updateClient(String clientId, String name) { }

    @Override
    public void getClient(String clientId) { }
}