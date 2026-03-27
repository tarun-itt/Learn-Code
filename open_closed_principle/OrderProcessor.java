public class OrderProcessor {
    
    private final DiscountStrategy discountStrategy;
    
    public OrderProcessor(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    
    public double processOrder(double cartValue) {
        double discount = discountStrategy.calculateDiscount(cartValue);
        double finalAmount = cartValue - discount;
        
        return finalAmount;
    }
}
