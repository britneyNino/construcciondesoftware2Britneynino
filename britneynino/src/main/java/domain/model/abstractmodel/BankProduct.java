package domain.model.abstractmodel;

import domain.model.enums.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public abstract class BankProduct {

    protected String productId;
    protected LocalDate creationDate;
    protected ProductCategory productCategory;
}
