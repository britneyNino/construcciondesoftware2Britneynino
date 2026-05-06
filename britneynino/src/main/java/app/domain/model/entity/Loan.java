package domain.model.entity;

import domain.model.abstractmodel.BankProduct;
import domain.model.abstractmodel.Client;
import domain.model.enums.LoanStatus;
import domain.model.enums.LoanType;
import domain.model.enums.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Loan extends BankProduct {

    private BigDecimal requestedAmount;   // Monto solicitado por el cliente
    private BigDecimal approvedAmount;    // Monto aprobado por el analista
    private BigDecimal interestRate;      // Tasa de interes anual
    private int termMonths;              // Plazo en meses
    private LoanType loanType;
    private LoanStatus status;
    private LocalDate approvalDate;
    private LocalDate disbursementDate;
    private Client client;

    // Cuenta destino donde se abona el desembolso (debe ser activa del cliente)
    private BankAccount disbursementAccount;

    public Loan(BigDecimal requestedAmount, LoanType loanType, int termMonths,
                BigDecimal interestRate, Client client) {
        this.requestedAmount = requestedAmount;
        this.loanType = loanType;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.client = client;
        this.status = LoanStatus.UNDER_REVIEW;  // Estado inicial segun enunciado
        this.productCategory = ProductCategory.LOAN;
        this.creationDate = LocalDate.now();
    }

    // Regla: solo UNDER_REVIEW puede ser aprobado (solo Analista Interno)
    public void approve(BigDecimal approvedAmount) {
        if (this.status != LoanStatus.UNDER_REVIEW) {
            throw new IllegalStateException("Only UNDER_REVIEW loans can be approved. Current: " + this.status);
        }
        if (approvedAmount == null || approvedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Approved amount must be greater than zero.");
        }
        this.approvedAmount = approvedAmount;
        this.status = LoanStatus.APPROVED;
        this.approvalDate = LocalDate.now();
    }

    // Regla: solo UNDER_REVIEW puede ser rechazado (solo Analista Interno)
    public void reject() {
        if (this.status != LoanStatus.UNDER_REVIEW) {
            throw new IllegalStateException("Only UNDER_REVIEW loans can be rejected. Current: " + this.status);
        }
        this.status = LoanStatus.REJECTED;
    }

    // Regla: solo APPROVED puede ser desembolsado
    public void disburse() {
        if (this.status != LoanStatus.APPROVED) {
            throw new IllegalStateException("Only APPROVED loans can be disbursed. Current: " + this.status);
        }
        if (this.disbursementAccount == null) {
            throw new IllegalStateException("Disbursement account must be defined before disbursing.");
        }
        if (!this.disbursementAccount.isOperable()) {
            throw new IllegalStateException("Disbursement account is not active.");
        }
        this.status = LoanStatus.DISBURSED;
        this.disbursementDate = LocalDate.now();
    }
}
