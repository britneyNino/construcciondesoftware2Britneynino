package app.domain.model.entity;

import app.domain.model.abstractmodel.Client;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("NATURAL_PERSON")
public class NaturalPersonClient extends Client {

	private String firstName;
	private String lastName;
	private LocalDate birthDate; // Validacion: debe ser mayor de 18 anos
}
