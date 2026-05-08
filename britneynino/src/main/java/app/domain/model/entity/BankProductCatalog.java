package app.domain.model.entity;

import app.domain.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bank_product_catalog")
public class BankProductCatalog {

	@Id
	private String productCode; // Unico

	private String productName;

	@Enumerated(EnumType.STRING)
	private ProductCategory category;

	private boolean requiresApproval; // Indica si requiere flujo de aprobacion
}
