package application.model.entity;

import java.util.Date;
import application.model.abstractmodel.BankProduct;
import application.model.enums.AccountStatus;
import.lombok.Getter;
import.lombok.Setter;

@getter
@setter

public class BankAccount extends BankProduct {

    private String accountNumber;
    private String accountType;
    private double currentBalance;
    private AccountStatus status;
    private Date openingDate;

}