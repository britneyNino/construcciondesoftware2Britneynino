package app.domain.model.entity;

import app.domain.model.enums.OperationType;
import app.domain.model.enums.SystemRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "operation_logs")
public class OperationLog {

	@Id
	@Column(name = "log_id")
	private String logId;

	@Enumerated(EnumType.STRING)
	private OperationType operationType;

	private LocalDateTime timestamp;

	// Trazabilidad completa segun enunciado
	private String userId;

	@Enumerated(EnumType.STRING)
	private SystemRole userRole;

	private String affectedProductId; // Cuenta, Prestamo o Transferencia

	// Datos variables segun el tipo de operacion (almacenamos como texto)
	@ElementCollection
	@CollectionTable(name = "operation_log_details", joinColumns = @JoinColumn(name = "log_id"))
	@MapKeyColumn(name = "detail_key")
	@Column(name = "detail_value")
	private Map<String, String> detailData;

	public OperationLog(OperationType operationType, String userId, SystemRole userRole, String affectedProductId) {
		this.operationType = operationType;
		this.userId = userId;
		this.userRole = userRole;
		this.affectedProductId = affectedProductId;
		this.timestamp = LocalDateTime.now();
		this.detailData = new HashMap<>();
		this.logId = UUID.randomUUID().toString();
	}

	@PrePersist
	private void prePersist() {
		if (this.logId == null) {
			this.logId = UUID.randomUUID().toString();
		}
		if (this.timestamp == null) {
			this.timestamp = LocalDateTime.now();
		}
		if (this.detailData == null) {
			this.detailData = new HashMap<>();
		}
	}

	// Registro inmutable: solo se puede agregar detalle, no modificar
	public void addDetail(String key, Object value) {
		this.detailData.put(key, String.valueOf(value));
	}
}
