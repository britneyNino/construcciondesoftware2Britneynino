package app.infrastructure.jpa.repositories;

import app.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByIdentification(String identification);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByIdentification(String identification);
	boolean existsByEmail(String email);
}
