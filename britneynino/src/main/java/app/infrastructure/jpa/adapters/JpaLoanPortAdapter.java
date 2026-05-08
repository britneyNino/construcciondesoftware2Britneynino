package app.infrastructure.jpa.adapters;

import app.domain.model.entity.Loan;
import app.domain.ports.out.LoanPort;
import app.infrastructure.jpa.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaLoanPortAdapter implements LoanPort {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public void save(Loan loan) {
		loanRepository.save(loan);
	}

	@Override
	public void update(Loan loan) {
		loanRepository.save(loan);
	}

	@Override
	public Loan findById(String loanId) {
		return loanRepository.findById(loanId).orElse(null);
	}

	@Override
	public List<Loan> findByClientIdentification(String identification) {
		return loanRepository.findByClientIdentification(identification);
	}
}
