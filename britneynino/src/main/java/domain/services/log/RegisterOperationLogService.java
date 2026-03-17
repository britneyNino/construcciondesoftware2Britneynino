package application.services.log;

import application.ports.OperationLogPort;

public class RegisterOperationLogService implements OperationLogPort {

    @Override
    public void registerOperation(String operationType) {

        if (operationType == null) {
            throw new IllegalArgumentException("Operation type required");
        }

        System.out.println("Operation logged");
    }
}