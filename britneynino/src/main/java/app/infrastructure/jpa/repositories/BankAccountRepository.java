package app.infrastructure.jpa.repositories;

import app.domain.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
	Optional<BankAccount> findByAccountNumber(String accountNumber);
	boolean existsByAccountNumber(String accountNumber);
	List<BankAccount> findByClientIdentification(String identification);
}
