package app.domain.services;

import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.User;
import app.domain.ports.out.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserService {

	private final UserPort userPort;

	@Autowired
	public FindUserService(UserPort userPort) {
		this.userPort = userPort;
	}

	public User findById(String userId) throws NotFoundException {
		User user = userPort.findById(userId);
		if (user == null) {
			throw new NotFoundException("User not found: " + userId);
		}
		return user;
	}

	public User findByUsername(String username) throws NotFoundException {
		User user = userPort.findByUsername(username);
		if (user == null) {
			throw new NotFoundException("User not found with username: " + username);
		}
		return user;
	}

	public List<User> findAll() {
		return userPort.findAll();
	}
}
