package app.domain.model.entity;

import app.domain.model.abstractmodel.Client;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("COMPANY")
public class CompanyClient extends Client {

	private String companyName; // Razon social
	private String nit; // Unico globalmente
	private String legalRepresentative; // Referencia a identificacion de Persona Natural
}
