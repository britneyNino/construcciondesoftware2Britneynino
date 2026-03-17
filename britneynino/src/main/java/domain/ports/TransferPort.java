package application.ports;

public interface TransferPort {

    void createTransfer(String sourceAccountId, String destinationAccountId, double amount);

    void executeTransfer(String transferId);

    void cancelTransfer(String transferId);

}