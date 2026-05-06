package domain.model.entity;

import domain.model.enums.TransferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Transfer {

    private String transferId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime approvalDate;
    private TransferStatus status;
    private String description;

    private BankAccount sourceAccount;
    private BankAccount destinationAccount;

    // Trazabilidad de quien creo y quien aprobo
    private String creatorUserId;
    private String approverUserId;

    // Umbral a partir del cual se requiere aprobacion del Supervisor de Empresa
    public static final BigDecimal HIGH_AMOUNT_THRESHOLD = new BigDecimal("10000000");

    public Transfer(BigDecimal amount, BankAccount sourceAccount,
                    BankAccount destinationAccount, String description, String creatorUserId) {
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.description = description;
        this.creatorUserId = creatorUserId;
        this.status = TransferStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // Regla enunciado: si supera el umbral, queda en espera de aprobacion
    public boolean requiresApproval() {
        return this.amount.compareTo(HIGH_AMOUNT_THRESHOLD) > 0;
    }

    public void markAsPendingApproval() {
        this.status = TransferStatus.PENDING_APPROVAL;
    }

    // Regla: solo PENDING_APPROVAL puede ser aprobada (solo Supervisor de Empresa)
    public void approve(String approverUserId) {
        if (this.status != TransferStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Only PENDING_APPROVAL transfers can be approved.");
        }
        this.status = TransferStatus.APPROVED;
        this.approverUserId = approverUserId;
        this.approvalDate = LocalDateTime.now();
    }

    // Regla: solo PENDING_APPROVAL puede ser rechazada (solo Supervisor de Empresa)
    public void reject(String approverUserId) {
        if (this.status != TransferStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Only PENDING_APPROVAL transfers can be rejected.");
        }
        this.status = TransferStatus.REJECTED;
        this.approverUserId = approverUserId;
        this.approvalDate = LocalDateTime.now();
    }

    // Regla: PENDING o APPROVED pueden ejecutarse (se mueven los fondos)
    public void execute() {
        if (this.status != TransferStatus.PENDING && this.status != TransferStatus.APPROVED) {
            throw new IllegalStateException("Transfer cannot be executed. Current status: " + this.status);
        }
        this.status = TransferStatus.EXECUTED;
    }

    // Regla enunciado: vence si lleva mas de 60 minutos en PENDING_APPROVAL
    public void expire() {
        if (this.status != TransferStatus.PENDING_APPROVAL) {
            throw new IllegalStateException("Only PENDING_APPROVAL transfers can expire.");
        }
        this.status = TransferStatus.EXPIRED;
    }
}
