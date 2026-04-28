package application.model.entity;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import application.model.enums.OperationType;

@Getter
@Setter
public class OperationLog {

    private String logId;
    private OperationType operationType;
    private LocalDateTime timestamp;
    private Map<String, Object> detailData;
}