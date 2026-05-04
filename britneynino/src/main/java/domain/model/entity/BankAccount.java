package domain.model.entity;

import domain.model.abstractmodel.BankProduct;
import domain.model.abstractmodel.Client;
import domain.model.enums.AccountStatus;
import domain.model.enums.AccountType;
import domain.model.enums.Currency;
import domain.model.enums.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BankAccount extends BankProduct {

    private String accountNumber;   // Unico
    private AccountType accountType;
    private BigDecimal currentBalance;
    private AccountStatus status;
    private Currency currency;
    private Client client;

    public BankAccount(String accountNumber, AccountType accountType,
                       BigDecimal initialBalance, Currency currency, Client client) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currentBalance = initialBalance;
        this.currency = currency;
        this.client = client;
        this.status = AccountStatus.ACTIVE;
        this.productCategory = ProductCategory.ACCOUNT;
        this.creationDate = LocalDate.now();
    }

    // Regla de negocio: la cuenta solo opera si esta ACTIVE
    public boolean isOperable() {
        return this.status == AccountStatus.ACTIVE;
    }

    // Regla: deposito valida estado y monto positivo
    public void deposit(BigDecimal amount) {
        if (!isOperable()) {
            throw new IllegalStateException("Account is not operable. Status: " + this.status);
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.currentBalance = this.currentBalance.add(amount);
    }

    // Regla: retiro valida estado, monto positivo y saldo suficiente
    public void withdraw(BigDecimal amount) {
        if (!isOperable()) {
            throw new IllegalStateException("Account is not operable. Status: " + this.status);
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount.compareTo(this.currentBalance) > 0) {
            throw new IllegalStateException("Insufficient balance.");
        }
        this.currentBalance = this.currentBalance.subtract(amount);
    }

    public void block() {
        this.status = AccountStatus.BLOCKED;
    }

    public void cancel() {
        this.status = AccountStatus.CANCELLED;
    }
}
