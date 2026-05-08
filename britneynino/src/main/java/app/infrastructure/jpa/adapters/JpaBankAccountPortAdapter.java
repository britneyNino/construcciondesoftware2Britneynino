package app.infrastructure.jpa.adapters;

import app.domain.model.entity.BankAccount;
import app.domain.ports.out.BankAccountPort;
import app.infrastructure.jpa.repositories.BankAccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class JpaBankAccountPortAdapter implements BankAccountPort {

	private final BankAccountRepository bankAccountRepository;

	public JpaBankAccountPortAdapter(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public boolean existsByAccountNumber(String accountNumber) {
		if (accountNumber == null) return false;
		return bankAccountRepository.existsByAccountNumber(accountNumber);
	}

	@Override
	@Transactional
	public void save(BankAccount bankAccount) {
		if (bankAccount == null) return;
		bankAccountRepository.save(bankAccount);
	}

	@Override
	@Transactional
	public void update(BankAccount bankAccount) {
		if (bankAccount == null) return;
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public BankAccount findByAccountNumber(String accountNumber) {
		if (accountNumber == null) return null;
		return bankAccountRepository.findByAccountNumber(accountNumber).orElse(null);
	}

	@Override
	public List<BankAccount> findByClientIdentification(String identification) {
		if (identification == null) return List.of();
		return Objects.requireNonNullElseGet(bankAccountRepository.findByClientIdentification(identification), List::of);
	}
}
