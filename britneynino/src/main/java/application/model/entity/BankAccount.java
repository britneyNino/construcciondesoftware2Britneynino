package application.model.entity;

import java.util.Date;
import application.model.abstractmodel.BankProduct;
import application.model.enums.AccountStatus;

public class BankAccount extends BankProduct {

    private String accountNumber;
    private String accountType;
    private double currentBalance;
    private AccountStatus status;
    private Date openingDate;

}