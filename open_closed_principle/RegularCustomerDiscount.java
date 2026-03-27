public class RegularCustomerDiscount implements DiscountStrategy {
    
    private static final double RATE = 0.10;
    
    @Override
    public double calculateDiscount(double cartValue) {
        return cartValue * RATE;
    }
    
    @Override
    public String getName() {
        return "Regular (10%)";
    }
}
