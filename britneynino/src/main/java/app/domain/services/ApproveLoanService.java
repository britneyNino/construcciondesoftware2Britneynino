package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.Loan;
import app.domain.model.entity.OperationLog;
import app.domain.model.enums.LoanStatus;
import app.domain.model.enums.OperationType;
import app.domain.model.enums.SystemRole;
import app.domain.ports.out.LoanPort;
import app.domain.ports.out.OperationLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ApproveLoanService {

	private final LoanPort loanPort;
	private final OperationLogPort operationLogPort;

	@Autowired
	public ApproveLoanService(LoanPort loanPort, OperationLogPort operationLogPort) {
		this.loanPort = loanPort;
		this.operationLogPort = operationLogPort;
	}

	// Regla enunciado: solo Analista Interno puede aprobar prestamos
	public void approveLoan(String loanId, BigDecimal approvedAmount, String analystUserId)
			throws BusinessException, NotFoundException {
		if (loanId == null || loanId.isBlank()) {
			throw new BusinessException("Loan id is required.");
		}
		Loan loan = loanPort.findById(loanId);
		if (loan == null) {
			throw new NotFoundException("Loan not found: " + loanId);
		}
		LoanStatus previousStatus = loan.getStatus();
		// Regla encapsulada en entidad: solo UNDER_REVIEW -> APPROVED
		loan.approve(approvedAmount);
		loanPort.update(loan);

		OperationLog log = new OperationLog(OperationType.LOAN_APPROVED, analystUserId, SystemRole.INTERNAL_ANALYST,
				loanId);
		log.addDetail("previousStatus", previousStatus.name());
		log.addDetail("newStatus", loan.getStatus().name());
		log.addDetail("approvedAmount", approvedAmount);
		log.addDetail("analystUserId", analystUserId);
		operationLogPort.append(log);
	}
}
