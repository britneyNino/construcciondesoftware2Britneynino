package domain.ports.in;

import domain.exceptions.BusinessException;
import domain.exceptions.NotFoundException;
import domain.model.entity.Loan;
import java.math.BigDecimal;
import java.util.List;

public interface LoanUseCase {
    void requestLoan(Loan loan) throws BusinessException;
    void approveLoan(String loanId, BigDecimal approvedAmount, String analystUserId) throws BusinessException, NotFoundException;
    void rejectLoan(String loanId, String analystUserId) throws BusinessException, NotFoundException;
    void disburseLoan(String loanId, String analystUserId) throws BusinessException, NotFoundException;
    Loan findById(String loanId) throws NotFoundException;
    List<Loan> findByClientIdentification(String identification);
}
