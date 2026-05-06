package app.domain.model.entity;

import app.domain.model.enums.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankProductCatalog {

    private String productCode;        // Unico
    private String productName;
    private ProductCategory category;
    private boolean requiresApproval;  // Indica si requiere flujo de aprobacion
}
