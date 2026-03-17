package application.services.user;

import application.ports.UserPort;

public class BlockUserService implements UserPort {

    @Override
    public void blockUser(String userId) {

        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        System.out.println("User blocked");
    }

    @Override
    public void createUser(String username, String password) { }

    @Override
    public void activateUser(String userId) { }

    @Override
    public String getUserRole(String userId) { 
        return null;
    }
}