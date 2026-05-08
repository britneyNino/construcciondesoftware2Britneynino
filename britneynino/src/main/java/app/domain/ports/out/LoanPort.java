package app.domain.ports.out;

import app.domain.model.entity.Loan;
import java.util.List;

public interface LoanPort {
	void save(Loan loan);
	void update(Loan loan);
	Loan findById(String loanId);
	List<Loan> findByClientIdentification(String identification);
}
