package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.model.entity.Loan;
import app.domain.model.entity.OperationLog;
import app.domain.model.enums.LoanStatus;
import app.domain.model.enums.OperationType;
import app.domain.model.enums.SystemRole;
import app.domain.ports.out.ClientPort;
import app.domain.ports.out.LoanPort;
import app.domain.ports.out.OperationLogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CreateLoanRequestService {

    private final LoanPort loanPort;
    private final ClientPort clientPort;
    private final OperationLogPort operationLogPort;

    @Autowired
    public CreateLoanRequestService(LoanPort loanPort, ClientPort clientPort, OperationLogPort operationLogPort) {
        this.loanPort = loanPort;
        this.clientPort = clientPort;
        this.operationLogPort = operationLogPort;
    }

    public void requestLoan(Loan loan, String creatorUserId, SystemRole creatorRole) throws BusinessException {
        if (loan.getClient() == null) {
            throw new BusinessException("A loan must be associated with a client.");
        }
        // Regla enunciado: cliente debe existir y estar activo
        if (!clientPort.existsByIdentification(loan.getClient().getIdentification())) {
            throw new BusinessException("The client does not exist.");
        }
        if (loan.getRequestedAmount() == null || loan.getRequestedAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Requested amount must be greater than zero.");
        }
        if (loan.getInterestRate() == null || loan.getInterestRate().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Interest rate must be greater than zero.");
        }
        if (loan.getTermMonths() <= 0) {
            throw new BusinessException("Loan term must be greater than zero.");
        }
        if (loan.getLoanType() == null) {
            throw new BusinessException("Loan type is required.");
        }
        // Estado inicial segun enunciado: En estudio
        loan.setStatus(LoanStatus.UNDER_REVIEW);
        loan.setCreationDate(LocalDate.now());
        loanPort.save(loan);

        OperationLog log = new OperationLog(OperationType.LOAN_REQUESTED, creatorUserId, creatorRole, loan.getProductId());
        log.addDetail("clientId", loan.getClient().getIdentification());
        log.addDetail("requestedAmount", loan.getRequestedAmount());
        log.addDetail("loanType", loan.getLoanType().name());
        operationLogPort.append(log);
    }
}
