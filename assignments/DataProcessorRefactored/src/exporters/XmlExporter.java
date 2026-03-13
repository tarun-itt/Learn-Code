package exporters;

import config.ProcessingConfiguration;
import interfaces.DataExporter;
import model.DataRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class XmlExporter implements DataExporter {
    private final ProcessingConfiguration config;

    public XmlExporter(ProcessingConfiguration config) {
        this.config = config;
    }

    @Override
    public String getFormat() {
        return "xml";
    }

    @Override
    public void export(List<DataRecord> records, String filePath) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(config.getDateFormat());

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<records>");

            for (DataRecord record : records) {
                String dateStr = record.getDate() != null
                        ? record.getDate().format(dateFormatter) : "";
                String doubled = record.getDoubledValue() != null
                        ? record.getDoubledValue().toString() : "";
                String squared = record.getSquaredValue() != null
                        ? record.getSquaredValue().toString() : "";

                writer.println("  <record>");
                writer.println("    <id>" + record.getId() + "</id>");
                writer.println("    <name>" + record.getName() + "</name>");
                writer.println("    <value>" + record.getValue() + "</value>");
                writer.println("    <date>" + dateStr + "</date>");
                writer.println("    <doubled_value>" + doubled + "</doubled_value>");
                writer.println("    <squared_value>" + squared + "</squared_value>");
                writer.println("  </record>");
            }

            writer.println("</records>");
        } catch (IOException e) {
            throw new RuntimeException("Failed to export XML: " + e.getMessage(), e);
        }
    }
}
