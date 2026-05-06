package domain.services;

import domain.exceptions.BusinessException;
import domain.model.entity.User;
import domain.model.enums.UserStatus;
import domain.ports.out.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateUserService {

    private final UserPort userPort;

    @Autowired
    public CreateUserService(UserPort userPort) {
        this.userPort = userPort;
    }

    public void createUser(User user) throws BusinessException {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new BusinessException("Username is required.");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BusinessException("Password is required.");
        }
        if (user.getIdentification() == null || user.getIdentification().isBlank()) {
            throw new BusinessException("Identification is required.");
        }
        if (userPort.existsByUsername(user.getUsername())) {
            throw new BusinessException("A user with that username already exists.");
        }
        if (userPort.existsByIdentification(user.getIdentification())) {
            throw new BusinessException("A user with that identification already exists.");
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new BusinessException("A valid email is required.");
        }
        if (userPort.existsByEmail(user.getEmail())) {
            throw new BusinessException("A user with that email already exists.");
        }
        if (user.getRole() == null) {
            throw new BusinessException("Role is required.");
        }
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedAt(LocalDate.now());
        userPort.save(user);
    }
}
