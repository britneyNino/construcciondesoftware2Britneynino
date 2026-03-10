package application.model.entity;

import application.model.enums.TransferStatus;
import java.time.LocalDate;
import.lombok.Getter;
import.lombok.Setter;

@getter
@setter

public class Transfer {

    private int transferId;
    private BankAccount sourceAccount;
    private BankAccount destinationAccount;
    private double amount;
    private TransferStatus status;
    private LocalDate creationDate;

}