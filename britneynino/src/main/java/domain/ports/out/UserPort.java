package domain.ports.out;

import domain.model.entity.User;
import java.util.List;

public interface UserPort {
    boolean existsByUsername(String username);
    boolean existsByIdentification(String identification);
    boolean existsByEmail(String email);
    void save(User user);
    void update(User user);
    User findById(String userId);
    User findByUsername(String username);
    List<User> findAll();
}
