package application.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import application.model.enums.TransferStatus;

@Getter
@Setter
public class Transfer {

    private String transferId;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransferStatus status;

    // Relaciones
    private BankAccount sourceAccount;
    private BankAccount destinationAccount;
}