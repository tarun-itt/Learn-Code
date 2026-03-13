public class Wallet {
    private double value;

    public Wallet(double value) {
        this.value = value;
    }

    public boolean hasEnough(double amount) {
        return value >= amount;
    }

    public void subtractMoney(double debit) {
        if (hasEnough(debit)) {
            value -= debit;
        }
    }

    public double getBalance() {
        return value;
    }
}
