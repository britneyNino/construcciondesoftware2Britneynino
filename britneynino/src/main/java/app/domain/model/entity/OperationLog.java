package domain.model.entity;

import domain.model.enums.OperationType;
import domain.model.enums.SystemRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class OperationLog {

    private String logId;
    private OperationType operationType;
    private LocalDateTime timestamp;

    // Trazabilidad completa segun enunciado
    private String userId;
    private SystemRole userRole;
    private String affectedProductId;  // Cuenta, Prestamo o Transferencia

    // Datos variables segun el tipo de operacion (NoSQL / documento)
    private Map<String, Object> detailData;

    public OperationLog(OperationType operationType, String userId,
                        SystemRole userRole, String affectedProductId) {
        this.operationType = operationType;
        this.userId = userId;
        this.userRole = userRole;
        this.affectedProductId = affectedProductId;
        this.timestamp = LocalDateTime.now();
        this.detailData = new HashMap<>();
    }

    // Registro inmutable: solo se puede agregar detalle, no modificar
    public void addDetail(String key, Object value) {
        this.detailData.put(key, value);
    }
}
