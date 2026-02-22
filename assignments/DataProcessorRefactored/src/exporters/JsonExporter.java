package exporters;

import config.ProcessingConfiguration;
import interfaces.DataExporter;
import model.DataRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonExporter implements DataExporter {
    private final ProcessingConfiguration config;

    public JsonExporter(ProcessingConfiguration config) {
        this.config = config;
    }

    @Override
    public String getFormat() {
        return "json";
    }

    @Override
    public void export(List<DataRecord> records, String filePath) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(config.getDateFormat());

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("[");

            for (int i = 0; i < records.size(); i++) {
                DataRecord record = records.get(i);
                String dateStr = record.getDate() != null
                        ? record.getDate().format(dateFormatter) : "";
                String doubled = record.getDoubledValue() != null
                        ? record.getDoubledValue().toString() : "";
                String squared = record.getSquaredValue() != null
                        ? record.getSquaredValue().toString() : "";

                String jsonObj = "  {"
                        + "\"id\": \"" + record.getId() + "\", "
                        + "\"name\": \"" + record.getName() + "\", "
                        + "\"value\": \"" + record.getValue() + "\", "
                        + "\"date\": \"" + dateStr + "\", "
                        + "\"doubled_value\": \"" + doubled + "\", "
                        + "\"squared_value\": \"" + squared + "\""
                        + "}";

                if (i < records.size() - 1) {
                    jsonObj += ",";
                }

                writer.println(jsonObj);
            }

            writer.println("]");
        } catch (IOException e) {
            throw new RuntimeException("Failed to export JSON: " + e.getMessage(), e);
        }
    }
}
