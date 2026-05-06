package application.services.user;

import application.ports.UserPort;

public class CreateUserService implements UserPort {

    @Override
    public void createUser(String username, String password) {

        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password are required");
        }

        System.out.println("User created");
    }

    @Override
    public void blockUser(String userId) { }

    @Override
    public void activateUser(String userId) { }

    @Override
    public String getUserRole(String userId) { 
        return null;
    }
}