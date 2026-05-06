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

@Service
public class RejectLoanService {

    private final LoanPort loanPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public RejectLoanService(LoanPort loanPort, OperationLogPort operationLogPort) {
        this.loanPort = loanPort;
        this.operationLogPort = operationLogPort;
    }

    // Regla enunciado: solo Analista Interno puede rechazar prestamos
    public void rejectLoan(String loanId, String analystUserId)
            throws BusinessException, NotFoundException {
        if (loanId == null || loanId.isBlank()) {
            throw new BusinessException("Loan id is required.");
        }
        Loan loan = loanPort.findById(loanId);
        if (loan == null) {
            throw new NotFoundException("Loan not found: " + loanId);
        }
        LoanStatus previousStatus = loan.getStatus();
        // Regla encapsulada en entidad: solo UNDER_REVIEW -> REJECTED
        loan.reject();
        loanPort.update(loan);

        OperationLog log = new OperationLog(OperationType.LOAN_REJECTED, analystUserId, SystemRole.INTERNAL_ANALYST, loanId);
        log.addDetail("previousStatus", previousStatus.name());
        log.addDetail("newStatus", loan.getStatus().name());
        log.addDetail("analystUserId", analystUserId);
        operationLogPort.append(log);
    }
}
