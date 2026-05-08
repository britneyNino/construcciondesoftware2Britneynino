package app.infrastructure.jpa.repositories;

import app.domain.model.entity.BankProductCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankProductCatalogRepository extends JpaRepository<BankProductCatalog, String> {
	Optional<BankProductCatalog> findByProductCode(String productCode);
	boolean existsByProductCode(String productCode);
}
