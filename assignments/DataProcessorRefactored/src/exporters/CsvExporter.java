package exporters;

import config.ProcessingConfiguration;
import interfaces.DataExporter;
import model.DataRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvExporter implements DataExporter {
    private final ProcessingConfiguration config;

    public CsvExporter(ProcessingConfiguration config) {
        this.config = config;
    }

    @Override
    public String getFormat() {
        return "csv";
    }

    @Override
    public void export(List<DataRecord> records, String filePath) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(config.getDateFormat());

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ID,NAME,VALUE,DATE,DOUBLED_VALUE,SQUARED_VALUE");

            for (DataRecord record : records) {
                String dateStr = record.getDate() != null
                        ? record.getDate().format(dateFormatter) : "";
                String doubled = record.getDoubledValue() != null
                        ? record.getDoubledValue().toString() : "";
                String squared = record.getSquaredValue() != null
                        ? record.getSquaredValue().toString() : "";

                writer.println(record.getId() + "," + record.getName() + ","
                        + record.getValue() + "," + dateStr + "," + doubled + "," + squared);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to export CSV: " + e.getMessage(), e);
        }
    }
}
