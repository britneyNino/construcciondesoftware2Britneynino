package application.model.entity;

import java.time.LocalDateTime;
import java.util.Map;

public class OperationLog {

    private String logId;
    private String operationType;
    private LocalDateTime timestamp;
    private Map<String, Object> detailData;

}