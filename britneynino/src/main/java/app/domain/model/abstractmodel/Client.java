package app.domain.model.abstractmodel;

import app.domain.model.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "client_type")
public abstract class Client {

	@Id
	protected String identification; // Unico globalmente (cedula o NIT)

	protected String email;
	protected String phone;
	protected String address;

	// Relacion explicita: un Client esta asociado a un User del sistema
	@ManyToOne
	@JoinColumn(name = "user_id")
	protected User user;
}
