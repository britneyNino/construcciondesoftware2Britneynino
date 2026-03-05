package application.model.entity;

import application.model.abstractmodel.BankProduct;
import application.model.enums.LoanStatus;
import java.time.LocalDate;

public class Loan extends BankProduct {

    private double requestedAmount;
    private double approvedAmount;
    private double interestRate;
    private int termMonths;
    private LoanStatus status;

    public Loan(String productId, LocalDate creationDate, double requestedAmount,
                double interestRate, int termMonths) {

        super(productId, creationDate);
        this.requestedAmount = requestedAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.status = LoanStatus.UNDER_REVIEW;
    }

}