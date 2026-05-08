package app.infrastructure.jpa.repositories;

import app.domain.model.entity.NaturalPersonClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaturalPersonClientRepository extends JpaRepository<NaturalPersonClient, String> {
	Optional<NaturalPersonClient> findByIdentification(String identification);
}
