package app.domain.ports.out;

import app.domain.model.entity.BankProductCatalog;
import java.util.List;

public interface BankProductCatalogPort {
    boolean existsByProductCode(String productCode);
    BankProductCatalog findByProductCode(String productCode);
    List<BankProductCatalog> findAll();
}
