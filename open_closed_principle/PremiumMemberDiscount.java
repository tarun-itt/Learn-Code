public class PremiumMemberDiscount implements DiscountStrategy {
    
    private static final double RATE = 0.20;
    
    @Override
    public double calculateDiscount(double cartValue) {
        return cartValue * RATE;
    }
    
    @Override
    public String getName() {
        return "Premium Member (20%)";
    }
}
