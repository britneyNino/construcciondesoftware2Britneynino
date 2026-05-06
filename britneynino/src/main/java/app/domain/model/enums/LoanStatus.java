package app.domain.model.enums;

// Enunciado: En estudio -> Aprobado/Rechazado -> Desembolsado -> En mora / Cancelado
public enum LoanStatus {
    UNDER_REVIEW,
    APPROVED,
    REJECTED,
    DISBURSED,
    IN_DEFAULT,
    CANCELLED
}
