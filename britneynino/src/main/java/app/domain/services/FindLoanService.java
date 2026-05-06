package app.domain.services;

import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.Loan;
import app.domain.ports.out.LoanPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindLoanService {

    private final LoanPort loanPort;

    @Autowired
    public FindLoanService(LoanPort loanPort) {
        this.loanPort = loanPort;
    }

    public Loan findById(String loanId) throws NotFoundException {
        Loan loan = loanPort.findById(loanId);
        if (loan == null) {
            throw new NotFoundException("Loan not found: " + loanId);
        }
        return loan;
    }

    public List<Loan> findByClientIdentification(String identification) {
        return loanPort.findByClientIdentification(identification);
    }
}
