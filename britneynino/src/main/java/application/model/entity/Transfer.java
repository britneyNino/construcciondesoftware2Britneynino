package application.model.entity;

import application.model.enums.TransferStatus;
import java.time.LocalDate;

public class Transfer {

    private int transferId;
    private BankAccount sourceAccount;
    private BankAccount destinationAccount;
    private double amount;
    private TransferStatus status;
    private LocalDate creationDate;

}