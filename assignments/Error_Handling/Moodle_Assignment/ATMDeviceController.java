public class ATMDeviceController {

    public void withdraw(String accountId, double amount)
            throws DeviceLockedException, NetworkConnectionException, InsufficientFundsException {

        DeviceHandle handle = getHandle(DEV1);
        DeviceRecord record = retrieveDeviceRecord(handle);

        validateDeviceStatus(record);
        validateConnection(record);
        validateBalance(accountId, amount);

        dispenseCash(handle, amount);
    }

    private void validateDeviceStatus(DeviceRecord record) throws DeviceLockedException {
        if (record.getStatus() == DEVICE_SUSPENDED) {
            throw new DeviceLockedException("ATM device is currently suspended.");
        }
    }

    private void validateConnection(DeviceRecord record) throws NetworkConnectionException {
        if (record.getWifiConnection() != WIFI_CONNECTED) {
            throw new NetworkConnectionException("No active network connection.");
        }
    }

    private void validateBalance(String accountId, double amount) throws InsufficientFundsException {
        if (getBalance(accountId) < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal of " + amount);
        }
    }
}
