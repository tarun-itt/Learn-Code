package services;

import config.ProcessingConfiguration;
import interfaces.*;
import model.DataRecord;

import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor {
    private final DataReader reader;
    private final DataValidator validator;
    private final DataTransformer transformer;
    private final Logger logger;
    private final ProcessingConfiguration config;
    private final StatisticsCalculator statisticsCalculator;
    private final Map<String, DataExporter> exporters;

    public DataProcessor(DataReader reader, DataValidator validator,
                         DataTransformer transformer, Logger logger,
                         ProcessingConfiguration config,
                         StatisticsCalculator statisticsCalculator,
                         List<DataExporter> exporterList) {
        this.reader = reader;
        this.validator = validator;
        this.transformer = transformer;
        this.logger = logger;
        this.config = config;
        this.statisticsCalculator = statisticsCalculator;
        this.exporters = new HashMap<>();

        for (DataExporter exporter : exporterList) {
            exporters.put(exporter.getFormat().toLowerCase(), exporter);
        }

        logger.log("DataProcessor initialized");
    }

    public ProcessingResult processData(String inputFilePath) {
        logger.log("Starting data processing");

        List<String> allErrors = new ArrayList<>();

        ReadResult readResult = reader.read(inputFilePath);
        allErrors.addAll(readResult.getErrors());
        List<DataRecord> records = new ArrayList<>(readResult.getRecords());

        if (config.shouldValidate()) {
            ValidationResult validationResult = validator.validate(records);
            allErrors.addAll(validationResult.getErrors());
            records = validationResult.getValidRecords();
        }

        if (config.shouldTransform()) {
            records = transformer.transform(records);
        }

        ProcessingStatistics statistics = statisticsCalculator.calculate(records, allErrors.size());

        logger.log("Processing complete. " + records.size()
                + " records processed with " + allErrors.size() + " errors");

        return new ProcessingResult(records, statistics, allErrors);
    }

    public void export(List<DataRecord> records, String filePath, String format) {
        DataExporter exporter = exporters.get(format.toLowerCase());

        if (exporter == null) {
            throw new IllegalArgumentException("Unsupported export format: " + format
                    + ". Available: " + String.join(", ", exporters.keySet()));
        }

        logger.log("Exporting " + records.size() + " records to " + format + ": " + filePath);
        exporter.export(records, filePath);
        logger.log(format.toUpperCase() + " export complete");
    }

    public List<DataRecord> filterByValue(List<DataRecord> records, double minValue) {
        List<DataRecord> filtered = records.stream()
                .filter(r -> r.getValue() >= minValue)
                .collect(Collectors.toList());

        logger.log("Filtered " + filtered.size() + " records with value >= " + minValue);
        return filtered;
    }
}
