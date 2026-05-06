package application.ports;

public interface UserPort {

    void createUser(String username, String password);

    void blockUser(String userId);

    void activateUser(String userId);

    String getUserRole(String userId);

}