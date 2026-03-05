package application.model.abstractmodel;

import java.util.Date;
import.lombok.Getter;
import.lombok.Setter;

@getter
@setter

public abstract class BankProduct {

    protected String productId;
    protected Date creationDate;

}