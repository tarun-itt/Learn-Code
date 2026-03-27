public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("=== Flipkart Cart: Regular Customer ===");
        OrderProcessor flipkartRegularOrder = new OrderProcessor(new RegularCustomerDiscount());
        flipkartRegularOrder.processOrder(2500);
        
        System.out.println("=== Amazon Cart: Premium Member ===");
        OrderProcessor amazonPremiumOrder = new OrderProcessor(new PremiumMemberDiscount());
        amazonPremiumOrder.processOrder(2500);
        
        System.out.println("=== Myntra Cart: Tiered (Rs 2500) ===");
        OrderProcessor myntraTieredOrder = new OrderProcessor(new TieredDiscount());
        myntraTieredOrder.processOrder(2500);
        
        System.out.println("=== Myntra Cart: Tiered (Rs 6000) ===");
        myntraTieredOrder.processOrder(6000);
    }
}
