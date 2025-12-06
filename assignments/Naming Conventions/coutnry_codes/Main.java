import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            CountryRegistry registry = new CountryRegistry();
            registry.loadFromFile("CountryCodes.csv", false);
            
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.print("\nEnter country code (or 'quit' to exit): ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("quit")) {
                    break;
                }
                
                processCountryCodeInput(input, registry);
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    private static void processCountryCodeInput(String input, CountryRegistry registry) {
        if (!input.isEmpty()) {

            String countryName = registry.getCountryName(input);
        
            if (countryName != null) {
                System.out.println("Country: " + countryName);
            } else {
                System.out.println("Country code '" + input + "' not found.");
            }
        } else {
            System.out.println("Please enter a valid country code.");
        }
    }
}