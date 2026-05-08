package app.domain.model.abstractmodel;

import app.domain.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BankProduct {

	@Id
	protected String productId;
	protected LocalDate creationDate;
	protected ProductCategory productCategory;
}
