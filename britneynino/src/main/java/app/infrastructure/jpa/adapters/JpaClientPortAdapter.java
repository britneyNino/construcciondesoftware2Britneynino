package app.infrastructure.jpa.adapters;

import app.domain.model.abstractmodel.Client;
import app.domain.model.entity.NaturalPersonClient;
import app.domain.model.entity.CompanyClient;
import app.domain.ports.out.ClientPort;
import app.infrastructure.jpa.repositories.NaturalPersonClientRepository;
import app.infrastructure.jpa.repositories.CompanyClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JpaClientPortAdapter implements ClientPort {

	private final NaturalPersonClientRepository naturalPersonClientRepository;
	private final CompanyClientRepository companyClientRepository;

	public JpaClientPortAdapter(NaturalPersonClientRepository naturalPersonClientRepository,
								CompanyClientRepository companyClientRepository) {
		this.naturalPersonClientRepository = naturalPersonClientRepository;
		this.companyClientRepository = companyClientRepository;
	}

	@Override
	public boolean existsByIdentification(String identification) {
		if (identification == null) return false;
		if (naturalPersonClientRepository.findByIdentification(identification).isPresent()) {
			return true;
		}
		return companyClientRepository.findByNit(identification).isPresent();
	}

	@Override
	@Transactional
	public void save(Client client) {
		if (client == null) return;
		if (client instanceof NaturalPersonClient) {
			naturalPersonClientRepository.save((NaturalPersonClient) client);
		} else if (client instanceof CompanyClient) {
			companyClientRepository.save((CompanyClient) client);
		}
	}

	@Override
	@Transactional
	public void update(Client client) {
		save(client);
	}

	@Override
	public Client findByIdentification(String identification) {
		if (identification == null) return null;
		Optional<NaturalPersonClient> naturalPerson = naturalPersonClientRepository.findByIdentification(identification);
		if (naturalPerson.isPresent()) return naturalPerson.get();
		Optional<CompanyClient> company = companyClientRepository.findByNit(identification);
		return company.orElse(null);
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		clients.addAll(naturalPersonClientRepository.findAll());
		clients.addAll(companyClientRepository.findAll());
		return clients;
	}
}
