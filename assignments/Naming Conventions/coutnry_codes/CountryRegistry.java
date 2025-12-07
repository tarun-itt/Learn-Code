import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CountryRegistry {
    private Map<String, String> countryNames = new HashMap<>();
    private Map<String, List<String>> adjacentCountries = new HashMap<>();

    public void loadFromJsonFile(String filePath) throws IOException {
        String countryDataJson = Files.readString(Path.of(filePath));

        List<JsonParser.CountryData> parsed = JsonParser.parse(countryDataJson);

        for (JsonParser.CountryData currentCountry : parsed) {
            countryNames.put(currentCountry.code.toUpperCase(), currentCountry.name);
            adjacentCountries.put(currentCountry.code.toUpperCase(), currentCountry.adjacentCountries);
        }
    }

    public String getCountryName(String code) {
        if (code == null) return null;
        return countryNames.get(code.toUpperCase());
    }

    public List<String> getAdjacentCountries(String code) {
        if (code == null) return null;
        return adjacentCountries.get(code.toUpperCase());
    }
}
