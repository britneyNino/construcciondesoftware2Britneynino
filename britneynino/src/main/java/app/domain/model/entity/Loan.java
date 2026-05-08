package app.domain.model.entity;

import app.domain.model.abstractmodel.BankProduct;
import app.domain.model.abstractmodel.Client;
import app.domain.model.enums.LoanStatus;
import app.domain.model.enums.LoanType;
import app.domain.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loan extends BankProduct {

	private BigDecimal requestedAmount; // Monto solicitado por el cliente
	private BigDecimal approvedAmount; // Monto aprobado por el analista
	private BigDecimal interestRate; // Tasa de interes anual
	private int termMonths; // Plazo en meses

	@Enumerated(EnumType.STRING)
	private LoanType loanType;

	@Enumerated(EnumType.STRING)
	private LoanStatus status;

	private LocalDate approvalDate;
	private LocalDate disbursementDate;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	// Cuenta destino donde se abona el desembolso (debe ser activa del cliente)
	@ManyToOne
	@JoinColumn(name = "disbursement_account_id")
	private BankAccount disbursementAccount;

	public Loan(BigDecimal requestedAmount, LoanType loanType, int termMonths, BigDecimal interestRate, Client client) {
		if (client == null) {
			throw new IllegalArgumentException("Client must not be null for a Loan");
		}
		this.requestedAmount = requestedAmount;
		this.loanType = loanType;
		this.termMonths = termMonths;
		this.interestRate = interestRate;
		this.client = client;
		this.status = LoanStatus.UNDER_REVIEW; // Estado inicial segun enunciado
		this.productCategory = ProductCategory.LOAN;
		this.creationDate = LocalDate.now();
		this.productId = UUID.randomUUID().toString();
	}

	@PrePersist
	private void prePersist() {
		if (this.productId == null) {
			this.productId = UUID.randomUUID().toString();
		}
		if (this.creationDate == null) {
			this.creationDate = LocalDate.now();
		}
	}

	// Regla: solo UNDER_REVIEW puede ser aprobado (solo Analista Interno)
	public void approve(BigDecimal approvedAmount) {
		if (this.status != LoanStatus.UNDER_REVIEW) {
			throw new IllegalStateException("Only UNDER_REVIEW loans can be approved. Current: " + this.status);
		}
		if (approvedAmount == null || approvedAmount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Approved amount must be positive.");
		}
		this.approvedAmount = approvedAmount;
		this.status = LoanStatus.APPROVED;
		this.approvalDate = LocalDate.now();
	}

	// Regla: solo APPROVED puede ser desembolsado
	public void disburse(BankAccount disbursementAccount) {
		if (this.status != LoanStatus.APPROVED) {
			throw new IllegalStateException("Only APPROVED loans can be disbursed. Current: " + this.status);
		}
		if (disbursementAccount == null) {
			throw new IllegalArgumentException("Disbursement account must not be null");
		}
		this.disbursementAccount = disbursementAccount;
		this.status = LoanStatus.DISBURSED;
		this.disbursementDate = LocalDate.now();
	}

	// Regla: solo UNDER_REVIEW puede ser rechazado
	public void reject() {
		if (this.status != LoanStatus.UNDER_REVIEW) {
			throw new IllegalStateException("Only UNDER_REVIEW loans can be rejected. Current: " + this.status);
		}
		this.status = LoanStatus.REJECTED;
	}
}
