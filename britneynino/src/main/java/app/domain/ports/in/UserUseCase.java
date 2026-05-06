package app.domain.ports.in;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.User;
import java.util.List;

public interface UserUseCase {
    void createUser(User user) throws BusinessException;
    void blockUser(String userId) throws BusinessException, NotFoundException;
    void activateUser(String userId) throws BusinessException, NotFoundException;
    User findById(String userId) throws NotFoundException;
    User findByUsername(String username) throws NotFoundException;
    List<User> findAll();
}
