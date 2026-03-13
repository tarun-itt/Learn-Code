import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            CountryRegistry registry = new CountryRegistry();
            registry.loadFromJsonFile("AdjacentCountries.json");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("\nEnter country code (or 'quit'): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("quit")) break;

                printResult(input, registry);
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(String code, CountryRegistry registry) {
        String countryName = registry.getCountryName(code);

        if (countryName != null) {
            System.out.println("Country: " + countryName);

            List<String> adjacentCountries = registry.getAdjacentCountries(code);
            System.out.println("Adjacent countries: " + adjacentCountries);
        } else {
            System.out.println("Country code not found.");
        }
    }
}