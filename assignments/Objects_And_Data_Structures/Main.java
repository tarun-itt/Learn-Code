public class Main {
    public static void main(String[] args) {
        Wallet wallet = new Wallet(50.0);
        Customer customer = new Customer("Tarun", "Sharma", wallet);
        Paperboy paperboy = new Paperboy();

        paperboy.collectPayment(customer, 20.0);
        paperboy.collectPayment(customer, 40.0);
    }
}
