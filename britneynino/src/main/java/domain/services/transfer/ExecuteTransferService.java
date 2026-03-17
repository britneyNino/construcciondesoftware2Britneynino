package application.services.transfer;

import application.ports.TransferPort;

public class ExecuteTransferService implements TransferPort {

    @Override
    public void executeTransfer(String transferId) {

        if (transferId == null) {
            throw new IllegalArgumentException("Transfer id cannot be null");
        }

        System.out.println("Transfer executed");
    }

    @Override
    public void createTransfer(String sourceAccountId, String destinationAccountId, double amount) { }

    @Override
    public void cancelTransfer(String transferId) { }
}