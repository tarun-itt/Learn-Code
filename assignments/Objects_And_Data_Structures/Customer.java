public class Customer {
    private String firstName;
    private String lastName;
    private Wallet myWallet;

    public Customer(String firstName, String lastName, Wallet wallet) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.myWallet = wallet;
    }

    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }

    public boolean pay(double amount) {
        if (myWallet.hasEnough(amount)) {
            myWallet.subtractMoney(amount);
            return true;
        }
        return false;
    }
}
