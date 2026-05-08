package app.infrastructure.jpa.repositories;

import app.domain.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
	List<Loan> findByClientIdentification(String identification);
}
