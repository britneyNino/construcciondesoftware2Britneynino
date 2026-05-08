package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.model.entity.BankAccount;
import app.domain.model.enums.AccountStatus;
import app.domain.model.enums.UserStatus;
import app.domain.model.abstractmodel.Client;
import app.domain.ports.out.BankAccountPort;
import app.domain.ports.out.ClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class OpenBankAccountService {

	private final BankAccountPort bankAccountPort;
	private final ClientPort clientPort;

	@Autowired
	public OpenBankAccountService(BankAccountPort bankAccountPort, ClientPort clientPort) {
		this.bankAccountPort = bankAccountPort;
		this.clientPort = clientPort;
	}

	public void openBankAccount(BankAccount bankAccount) throws BusinessException {
		if (bankAccount.getAccountNumber() == null || bankAccount.getAccountNumber().isBlank()) {
			throw new BusinessException("Account number is required.");
		}
		// Regla enunciado: numero de cuenta unico
		if (bankAccountPort.existsByAccountNumber(bankAccount.getAccountNumber())) {
			throw new BusinessException("An account with that number already exists.");
		}
		if (bankAccount.getClient() == null) {
			throw new BusinessException("A bank account must be associated with a client.");
		}
		// Regla enunciado: cliente debe existir y no estar inactivo/bloqueado
		Client client = clientPort.findByIdentification(bankAccount.getClient().getIdentification());
		if (client == null) {
			throw new BusinessException("The client does not exist.");
		}
		if (client.getUser() == null) {
			throw new BusinessException("Client has no associated system user.");
		}
		if (client.getUser().getStatus() == UserStatus.INACTIVE || client.getUser().getStatus() == UserStatus.BLOCKED) {
			throw new BusinessException("The client is not active or is blocked.");
		}
		if (bankAccount.getAccountType() == null) {
			throw new BusinessException("Account type is required.");
		}
		if (bankAccount.getCurrency() == null) {
			throw new BusinessException("Currency is required.");
		}
		if (bankAccount.getCurrentBalance() == null || bankAccount.getCurrentBalance().compareTo(BigDecimal.ZERO) < 0) {
			throw new BusinessException("Initial balance cannot be negative.");
		}
		bankAccount.setStatus(AccountStatus.ACTIVE);
		bankAccount.setCreationDate(LocalDate.now());
		bankAccountPort.save(bankAccount);
	}
}
