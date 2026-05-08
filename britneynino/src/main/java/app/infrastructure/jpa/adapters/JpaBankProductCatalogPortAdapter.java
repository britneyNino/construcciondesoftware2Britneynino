package app.infrastructure.jpa.adapters;

import app.domain.model.entity.BankProductCatalog;
import app.domain.ports.out.BankProductCatalogPort;
import app.infrastructure.jpa.repositories.BankProductCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaBankProductCatalogPortAdapter implements BankProductCatalogPort {

	@Autowired
	private BankProductCatalogRepository bankProductCatalogRepository;

	@Override
	public boolean existsByProductCode(String productCode) {
		return bankProductCatalogRepository.existsByProductCode(productCode);
	}

	@Override
	public BankProductCatalog findByProductCode(String productCode) {
		return bankProductCatalogRepository.findByProductCode(productCode).orElse(null);
	}

	@Override
	public List<BankProductCatalog> findAll() {
		return bankProductCatalogRepository.findAll();
	}
}
