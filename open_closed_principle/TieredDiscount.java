public class TieredDiscount implements DiscountStrategy {
    
    @Override
    public double calculateDiscount(double cartValue) {
        if (cartValue >= 5000) {
            return cartValue * 0.15;
        } else if (cartValue >= 2000) {
            return cartValue * 0.10;
        } else if (cartValue >= 1000) {
            return cartValue * 0.05;
        }
        return 0;
    }
    
    @Override
    public String getName() {
        return "Tiered (5-15%)";
    }
}
