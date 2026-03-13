package services;

import config.ProcessingConfiguration;
import interfaces.DataTransformer;
import interfaces.Logger;
import model.DataRecord;

import java.util.ArrayList;
import java.util.List;

public class RecordTransformer implements DataTransformer {
    private final ProcessingConfiguration config;
    private final Logger logger;

    public RecordTransformer(ProcessingConfiguration config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    @Override
    public List<DataRecord> transform(List<DataRecord> records) {
        logger.log("Transforming data...");

        List<DataRecord> transformed = new ArrayList<>();

        for (DataRecord record : records) {
            DataRecord result = transformRecord(record);
            transformed.add(result);
        }

        logger.log("Transformation complete");
        return transformed;
    }

    private DataRecord transformRecord(DataRecord record) {
        DataRecord result = record.withTransformedName(record.getName().toUpperCase());

        if (result.getDate() != null) {
            result = result.withFormattedDate(result.getDate());
        }

        double doubled = result.getValue() * 2;
        double squared = result.getValue() * result.getValue();
        result = result.withDerivedValues(doubled, squared);

        return result;
    }
}
