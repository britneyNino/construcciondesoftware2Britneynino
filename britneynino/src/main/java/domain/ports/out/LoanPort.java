package domain.ports.out;

import domain.model.entity.Loan;
import java.util.List;

public interface LoanPort {
    void save(Loan loan);
    void update(Loan loan);
    Loan findById(String loanId);
    List<Loan> findByClientIdentification(String identification);
}
