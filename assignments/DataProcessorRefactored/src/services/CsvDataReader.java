package services;

import interfaces.DataReader;
import interfaces.Logger;
import interfaces.ReadResult;
import model.DataRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader implements DataReader {
    private final Logger logger;

    public CsvDataReader(Logger logger) {
        this.logger = logger;
    }

    @Override
    public ReadResult read(String filePath) {
        logger.log("Reading input file: " + filePath);

        List<DataRecord> records = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            logger.log("Read " + lines.size() + " lines");

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                DataRecord record = parseLine(line, errors);
                if (record != null) {
                    records.add(record);
                }
            }
        } catch (IOException e) {
            errors.add("Failed to read file: " + e.getMessage());
            logger.log("ERROR: Failed to read file: " + e.getMessage());
        }

        logger.log("Parsed " + records.size() + " records");
        return new ReadResult(records, errors);
    }

    private DataRecord parseLine(String line, List<String> errors) {
        String[] parts = line.split(",");

        if (parts.length < 3) {
            errors.add("Invalid line format: " + line);
            logger.log("ERROR: Invalid line format: " + line);
            return null;
        }

        String id = parts[0].trim();
        String name = parts[1].trim();
        String valueStr = parts[2].trim();

        double value;
        try {
            value = Double.parseDouble(valueStr);
        } catch (NumberFormatException e) {
            errors.add("Invalid numeric value in line: " + line);
            logger.log("ERROR: Invalid numeric value in line: " + line);
            return null;
        }

        LocalDate date = null;
        if (parts.length >= 4) {
            try {
                date = LocalDate.parse(parts[3].trim());
            } catch (DateTimeParseException e) {
                // Date parsing failed; leave date as null
            }
        }

        return new DataRecord(id, name, value, date);
    }
}
