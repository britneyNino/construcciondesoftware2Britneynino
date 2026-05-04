package domain.ports.in;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.User;
import java.util.List;

public interface UserUseCase {
    void createUser(User user) throws BusinessException;
    void blockUser(String userId) throws BusinessException, NotFoundException;
    void activateUser(String userId) throws BusinessException, NotFoundException;
    User findById(String userId) throws NotFoundException;
    User findByUsername(String username) throws NotFoundException;
    List<User> findAll();
}
