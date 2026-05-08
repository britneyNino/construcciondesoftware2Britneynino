package app.domain.services;

import app.domain.exceptions.NotFoundException;
import app.domain.model.entity.BankProductCatalog;
import app.domain.ports.out.BankProductCatalogPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProductCatalogService {

	private final BankProductCatalogPort bankProductCatalogPort;

	@Autowired
	public FindProductCatalogService(BankProductCatalogPort bankProductCatalogPort) {
		this.bankProductCatalogPort = bankProductCatalogPort;
	}

	public BankProductCatalog findByProductCode(String productCode) throws NotFoundException {
		if (!bankProductCatalogPort.existsByProductCode(productCode)) {
			throw new NotFoundException("Product not found in catalog: " + productCode);
		}
		return bankProductCatalogPort.findByProductCode(productCode);
	}

	public List<BankProductCatalog> findAll() {
		return bankProductCatalogPort.findAll();
	}
}
