package application.services.transfer;

import application.ports.TransferPort;

public class CreateTransferService implements TransferPort {

    @Override
    public void createTransfer(String sourceAccountId, String destinationAccountId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        System.out.println("Transfer created");
    }

    @Override
    public void executeTransfer(String transferId) { }

    @Override
    public void cancelTransfer(String transferId) { }
}