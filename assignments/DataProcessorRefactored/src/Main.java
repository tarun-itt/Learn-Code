import config.ProcessingConfiguration;
import exporters.CsvExporter;
import exporters.JsonExporter;
import exporters.XmlExporter;
import interfaces.*;
import logging.FileLogger;
import model.DataRecord;
import services.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input.csv";
        String outputFile = "output.csv";

        generateSampleData(inputFile, 50);

        ProcessingConfiguration config = new ProcessingConfiguration(
                "MM/dd/yyyy", 50, true, true
        );

        Logger logger = new FileLogger("processing.log");

        DataReader reader = new CsvDataReader(logger);
        DataValidator validator = new RecordValidator(logger);
        DataTransformer transformer = new RecordTransformer(config, logger);
        StatisticsCalculator statisticsCalculator = new StatisticsCalculator(logger);

        List<DataExporter> exporters = new ArrayList<>();
        exporters.add(new CsvExporter(config));
        exporters.add(new JsonExporter(config));
        exporters.add(new XmlExporter(config));

        DataProcessor processor = new DataProcessor(
                reader, validator, transformer, logger, config, statisticsCalculator, exporters
        );

        ProcessingResult result = processor.processData(inputFile);

        processor.export(result.getRecords(), outputFile, "csv");

        displayStatistics(result);

        processor.export(result.getRecords(), "output.json", "json");
        processor.export(result.getRecords(), "output.xml", "xml");

        List<DataRecord> filtered = processor.filterByValue(result.getRecords(), 100);
        System.out.println("\nFiltered records (value >= 100): " + filtered.size());

        System.out.println("\nRecords processed: " + result.getRecordsProcessed());
        System.out.println("Errors: " + result.getErrorCount());

        logger.flush();
    }

    private static void displayStatistics(ProcessingResult result) {
        System.out.println("\n=== Processing Statistics ===");
        System.out.println("Total Records: " + result.getStatistics().getTotalRecords());
        System.out.println("Error Count: " + result.getStatistics().getErrorCount());
        System.out.printf("Total Value: %.2f%n", result.getStatistics().getTotalValue());
        System.out.printf("Average Value: %.2f%n", result.getStatistics().getAverageValue());

        if (!result.getErrors().isEmpty()) {
            System.out.println("\n=== Errors ===");
            for (String error : result.getErrors()) {
                System.out.println("- " + error);
            }
        }
    }

    private static void generateSampleData(String filePath, int recordCount) {
        Random random = new Random();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 1; i <= recordCount; i++) {
                String id = String.format("ID%04d", i);
                String name = "Item" + i;
                double value = random.nextInt(990) + 10;
                LocalDate date = LocalDate.now().minusDays(random.nextInt(365));

                writer.println(id + "," + name + "," + value + "," + date);
            }
        } catch (IOException e) {
            System.err.println("Failed to generate sample data: " + e.getMessage());
        }

        System.out.println("Generated " + recordCount + " sample records in " + filePath);
    }
}
