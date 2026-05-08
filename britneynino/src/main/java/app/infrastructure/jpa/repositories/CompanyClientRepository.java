package app.infrastructure.jpa.repositories;

import app.domain.model.entity.CompanyClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyClientRepository extends JpaRepository<CompanyClient, String> {
	Optional<CompanyClient> findByNit(String nit);
}
