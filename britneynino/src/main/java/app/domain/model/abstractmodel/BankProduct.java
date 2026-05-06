package application.model.abstractmodel;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import application.model.enums.ProductCategory;

@Getter
@Setter
public abstract class BankProduct {

    protected String productId;
    protected LocalDate creationDate;
    protected ProductCategory productCategory;

}