import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountryRegistry {
    private Map<String, String> countryMap;

    public CountryRegistry() {
        countryMap = new HashMap<>();
    }

    public void loadFromFile(String filePath, boolean skipHeader) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (skipHeader) {
                skipHeader = false;
                continue;
            }

            parseCsvLine(line);
        }

        reader.close();
    }

    private void parseCsvLine(String line) {
        String[] tokens = line.split(",", 2);
        
        if (tokens.length == 2) {
            String countryCode = tokens[0].trim().toUpperCase();
            String countryName = tokens[1].trim();

            if (!countryCode.isEmpty() && !countryName.isEmpty()) {
                countryMap.put(countryCode, countryName);
            }
        }
    }

    public String getCountryName(String countryCode) {
        if (countryCode == null) return null;
        return countryMap.get(countryCode.toUpperCase());
    }
}
