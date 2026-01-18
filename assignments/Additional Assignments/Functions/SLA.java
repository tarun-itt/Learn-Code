public class SLA {
    public static double discountRate = 0.10;
    public static double taxRate = 0.18;
    public static int minOrderId = 0;
    
    public static void placeOrder(int orderId, double orderAmount) {
        if (isOrderInvalid(orderId)) {
            showInvalidOrderMessage();
            return;
        }

        double finalAmount = calculateFinalBill(orderAmount);
        
        saveOrder(orderId, finalAmount);
        notifySuccess();
    }


    private static double calculateFinalBill(double orderAmount) {
        double discountAmount = calculateDiscount(orderAmount);
        double tax = calculateTax(orderAmount);
        return (orderAmount + tax) - discountAmount;
    }

    private static double calculateDiscount(double amount) {
        return amount * discountRate;
    }

    private static boolean isOrderInvalid(int orderId) {
        return orderId <= minOrderId;
    }

    private static double calculateTax(double amount) {
        return amount * taxRate;
    }

    private static void showInvalidOrderMessage() {
        System.out.println("Order cannot be empty/invalid");
    }

    private static void saveOrder(int orderId, double amount) {
        System.out.println("Order " + orderId + " saved in database with amount: " + amount);
    }

    private static void notifySuccess() {
        System.out.println("Order placed successfully");
    }

    public static void main(String[] args) {
        placeOrder(101, 100.0);
    }
}
