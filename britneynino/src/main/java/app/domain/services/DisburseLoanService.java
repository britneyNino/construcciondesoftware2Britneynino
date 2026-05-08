package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.BankAccount;
import app.domain.model.entity.Loan;
import app.domain.model.entity.OperationLog;
import app.domain.model.enums.OperationType;
import app.domain.model.enums.SystemRole;
import app.domain.ports.out.BankAccountPort;
import app.domain.ports.out.LoanPort;
import app.domain.ports.out.OperationLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisburseLoanService {

	private final LoanPort loanPort;
	private final BankAccountPort bankAccountPort;
	private final OperationLogPort operationLogPort;

	@Autowired
	public DisburseLoanService(LoanPort loanPort, BankAccountPort bankAccountPort, OperationLogPort operationLogPort) {
		this.loanPort = loanPort;
		this.bankAccountPort = bankAccountPort;
		this.operationLogPort = operationLogPort;
	}

	// Regla enunciado: solo Analista Interno puede desembolsar
	public void disburseLoan(String loanId, String analystUserId) throws BusinessException, NotFoundException {
		if (loanId == null || loanId.isBlank()) {
			throw new BusinessException("Loan id is required.");
		}
		Loan loan = loanPort.findById(loanId);
		if (loan == null) {
			throw new NotFoundException("Loan not found: " + loanId);
		}
		// Regla enunciado: cuenta destino debe estar definida y activa
		if (loan.getDisbursementAccount() == null) {
			throw new BusinessException("Disbursement account must be defined.");
		}
		BankAccount disbursementAccount = bankAccountPort
				.findByAccountNumber(loan.getDisbursementAccount().getAccountNumber());
		if (disbursementAccount == null) {
			throw new NotFoundException("Disbursement account not found.");
		}
		if (!disbursementAccount.isOperable()) {
			throw new BusinessException("Disbursement account is not active.");
		}
		// Regla enunciado: monto aprobado mayor que cero
		if (loan.getApprovedAmount() == null || loan.getApprovedAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
			throw new BusinessException("Approved amount must be greater than zero.");
		}
		// Regla encapsulada en entidad: solo APPROVED -> DISBURSED
		loan.disburse(disbursementAccount);
		// Regla enunciado: aumentar saldo de cuenta destino por monto aprobado
		disbursementAccount.deposit(loan.getApprovedAmount());

		loanPort.update(loan);
		bankAccountPort.update(disbursementAccount);

		// Bitacora obligatoria segun enunciado
		OperationLog log = new OperationLog(OperationType.LOAN_DISBURSED, analystUserId, SystemRole.INTERNAL_ANALYST,
				loanId);
		log.addDetail("approvedAmount", loan.getApprovedAmount());
		log.addDetail("disbursementAccount", disbursementAccount.getAccountNumber());
		log.addDetail("balanceAfterDisbursement", disbursementAccount.getCurrentBalance());
		log.addDetail("analystUserId", analystUserId);
		operationLogPort.append(log);
	}
}
