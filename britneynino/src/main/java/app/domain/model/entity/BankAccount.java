package application.model.entity;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import application.model.abstractmodel.BankProduct;
import application.model.abstractmodel.Client;
import application.model.enums.AccountStatus;
import application.model.enums.AccountType;
import application.model.enums.Currency;

@Getter
@Setter
public class BankAccount extends BankProduct {

    private AccountType accountType;
    private BigDecimal currentBalance;
    private AccountStatus status;
    private Currency currency;

    // Relación
    private Client client;

    // Regla de negocio básica
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.currentBalance = this.currentBalance.add(amount);
    }
}