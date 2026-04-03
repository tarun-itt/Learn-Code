public class TryCatchFinallyExample {
    public static void main(String[] args) {
        System.out.println("--- Normal Flow ---");
        executeScenario(false);

        System.out.println("\n--- Error Flow ---");
        executeScenario(true);
    }

    private static void executeScenario(boolean throwError) {
        try {
            System.out.println("1. In try block");
            if (throwError) {
                throw new RuntimeException("Simulated error");
            }
            System.out.println("2. Try block finished");
            
        } catch (RuntimeException e) {
            System.out.println("3. Caught exception: " + e.getMessage());
            
        } finally {
            System.out.println("4. In finally block - doing cleanup");
        }
        
        System.out.println("5. Execution continues");
    }
}
