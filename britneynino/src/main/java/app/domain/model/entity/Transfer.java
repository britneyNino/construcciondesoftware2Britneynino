package app.domain.model.entity;

import app.domain.model.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transfers")
public class Transfer {

	@Id
	@Column(name = "transfer_id")
	private String transferId;

	private BigDecimal amount;
	private LocalDateTime createdAt;
	private LocalDateTime approvalDate;

	@Enumerated(EnumType.STRING)
	private TransferStatus status;

	private String description;

	@ManyToOne
	@JoinColumn(name = "source_account_id")
	private BankAccount sourceAccount;

	@ManyToOne
	@JoinColumn(name = "destination_account_id")
	private BankAccount destinationAccount;

	// Trazabilidad de quien creo y quien aprobo
	@Column(name = "creator_user_id")
	private String creatorUserId;

	@Column(name = "approver_user_id")
	private String approverUserId;

	// Umbral a partir del cual se requiere aprobacion del Supervisor de Empresa
	public static final BigDecimal HIGH_AMOUNT_THRESHOLD = new BigDecimal("10000000");

	public Transfer(BigDecimal amount, BankAccount sourceAccount, BankAccount destinationAccount, String description,
			String creatorUserId) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.amount = amount;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.description = description;
		this.creatorUserId = creatorUserId;
		this.status = TransferStatus.PENDING;
		this.createdAt = LocalDateTime.now();
		this.transferId = UUID.randomUUID().toString();
	}

	@PrePersist
	private void prePersist() {
		if (this.transferId == null) {
			this.transferId = UUID.randomUUID().toString();
		}
		if (this.createdAt == null) {
			this.createdAt = LocalDateTime.now();
		}
	}

	// Regla enunciado: si supera el umbral, queda en espera de aprobacion
	public boolean requiresApproval() {
		return this.amount != null && this.amount.compareTo(HIGH_AMOUNT_THRESHOLD) > 0;
	}

	public void markAsPendingApproval() {
		this.status = TransferStatus.PENDING_APPROVAL;
	}

	public void approve(String approverUserId) {
		this.status = TransferStatus.APPROVED;
		this.approverUserId = approverUserId;
		this.approvalDate = LocalDateTime.now();
	}

	public void reject(String rejectorUserId) {
		this.status = TransferStatus.REJECTED;
		this.approverUserId = rejectorUserId;
	}

	public void expire() {
		if (this.status == TransferStatus.PENDING_APPROVAL) {
			this.status = TransferStatus.EXPIRED;
		}
	}
	public void execute() {
		this.status = TransferStatus.EXECUTED;
		this.approvalDate = LocalDateTime.now();
	}

}
