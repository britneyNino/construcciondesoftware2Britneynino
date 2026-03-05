package application.model.entity;

import java.time.LocalDateTime;
import java.util.Map;
import.lombok.Getter;
import.lombok.Setter;

@getter
@setter

public class OperationLog {

    private String logId;
    private String operationType;
    private LocalDateTime timestamp;
    private Map<String, Object> detailData;

}