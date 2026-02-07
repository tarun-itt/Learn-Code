public class Paperboy {
    public void collectPayment(Customer customer, double paymentAmount) {
        if (customer.pay(paymentAmount)) {
            System.out.println("Payment of " + paymentAmount + " collected from " + customer.getFirstName() + " " + customer.getLastName());
        } else {
            System.out.println("Customer " + customer.getFirstName() + " " + customer.getLastName() + " does not have enough money.");
        }
    }
}
