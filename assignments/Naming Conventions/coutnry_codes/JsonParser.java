import java.util.*;

public class JsonParser {

    public static class CountryData {
        public String code;
        public String name;
        public List<String> adjacentCountries;

        public CountryData(String code, String name, List<String> adjacentCountries) {
            this.code = code;
            this.name = name;
            this.adjacentCountries = adjacentCountries;
        }
    }

    public static List<CountryData> parse(String json) {
        List<CountryData> result = new ArrayList<>();

        json = json.trim();
        if (json.startsWith("[")) json = json.substring(1);
        if (json.endsWith("]")) json = json.substring(0, json.length() - 1);

        String[] blocks = json.split("\\},");
        for (String block : blocks) {
            block = block.replace("{", "").replace("}", "").trim();
            if (block.isEmpty()) continue;

            String code = extract(block, "\"code\":");
            String name = extract(block, "\"country\":");
            List<String> adjacentCountries = extractList(block, "\"adjacentCountries\":");

            result.add(new CountryData(code, name, adjacentCountries));
        }

        return result;
    }

    private static String extract(String text, String key) {
        int start = text.indexOf(key);
        if (start == -1) return null;

        start = text.indexOf("\"", start + key.length()) + 1;
        int end = text.indexOf("\"", start);

        return text.substring(start, end).trim();
    }

    private static List<String> extractList(String text, String key) {
        int start = text.indexOf(key);
        if (start == -1) return Collections.emptyList();

        start = text.indexOf("[", start) + 1;
        int end = text.indexOf("]", start);

        String raw = text.substring(start, end).trim();
        if (raw.isEmpty()) return Collections.emptyList();

        String[] parts = raw.split(",");
        List<String> list = new ArrayList<>();

        for (String p : parts) {
            list.add(p.replace("\"", "").trim());
        }
        return list;
    }
}
