package application.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import application.model.abstractmodel.BankProduct;
import application.model.abstractmodel.Client;
import application.model.enums.LoanStatus;
import application.model.enums.LoanType;

@Getter
@Setter
public class Loan extends BankProduct {

    private BigDecimal amount;
    private LoanType loanType;
    private LoanStatus status;
    private int termMonths;
    private LocalDate approvalDate;

    // Relación
    private Client client;
}