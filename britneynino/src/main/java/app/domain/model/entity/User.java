package app.domain.model.entity;

import app.domain.model.enums.SystemRole;
import app.domain.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	private String userId;

	private String username;
	private String password;
	private String fullName;
	private String identification; // Unico globalmente
	private String email;
	private String phone;
	private String address;
	private LocalDate birthDate;

	@Enumerated(EnumType.STRING)
	private SystemRole role;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	private LocalDate createdAt;

	// ID del cliente (Persona Natural o Empresa) al que pertenece este usuario
	// Nulo para Analista Interno y Empleado del banco sin cliente asociado
	private String clientId;
}
