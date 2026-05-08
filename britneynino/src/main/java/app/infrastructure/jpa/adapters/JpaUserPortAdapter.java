package app.infrastructure.jpa.adapters;

import app.domain.model.entity.User;
import app.domain.ports.out.UserPort;
import app.infrastructure.jpa.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class JpaUserPortAdapter implements UserPort {

	private final UserRepository userRepository;

	public JpaUserPortAdapter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean existsByUsername(String username) {
		if (username == null) return false;
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByIdentification(String identification) {
		if (identification == null) return false;
		return userRepository.existsByIdentification(identification);
	}

	@Override
	public boolean existsByEmail(String email) {
		if (email == null) return false;
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional
	public void save(User user) {
		if (user == null) return;
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		if (user == null) return;
		userRepository.save(user);
	}

	@Override
	public User findById(String userId) {
		if (userId == null) return null;
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User findByUsername(String username) {
		if (username == null) return null;
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public List<User> findAll() {
		return Objects.requireNonNullElseGet(userRepository.findAll(), List::of);
	}
}
