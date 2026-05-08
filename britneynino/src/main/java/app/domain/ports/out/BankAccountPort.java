package app.domain.ports.out;

import app.domain.model.entity.BankAccount;
import java.util.List;

public interface BankAccountPort {
	boolean existsByAccountNumber(String accountNumber);
	void save(BankAccount bankAccount);
	void update(BankAccount bankAccount);
	BankAccount findByAccountNumber(String accountNumber);
	List<BankAccount> findByClientIdentification(String identification);
}
