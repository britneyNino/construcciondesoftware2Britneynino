package domain.services;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.User;
import domain.model.enums.UserStatus;
import domain.ports.out.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockUserService {

    private final UserPort userPort;

    @Autowired
    public BlockUserService(UserPort userPort) {
        this.userPort = userPort;
    }

    public void blockUser(String userId) throws BusinessException, NotFoundException {
        if (userId == null || userId.isBlank()) {
            throw new BusinessException("User id is required.");
        }
        User user = userPort.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found: " + userId);
        }
        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new BusinessException("User is already blocked.");
        }
        user.setStatus(UserStatus.BLOCKED);
        userPort.update(user);
    }
}
