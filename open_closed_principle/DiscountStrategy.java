public interface DiscountStrategy {
    
    double calculateDiscount(double cartValue);
    
    String getName();
}
