package services;

import interfaces.DataValidator;
import interfaces.Logger;
import interfaces.ValidationResult;
import model.DataRecord;

import java.util.ArrayList;
import java.util.List;

public class RecordValidator implements DataValidator {
    private final Logger logger;

    public RecordValidator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public ValidationResult validate(List<DataRecord> records) {
        logger.log("Validating data...");

        List<DataRecord> validRecords = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (DataRecord record : records) {
            List<String> recordErrors = validateRecord(record);

            if (recordErrors.isEmpty()) {
                validRecords.add(record);
            } else {
                errors.addAll(recordErrors);
            }
        }

        logger.log("Validation complete. " + validRecords.size() + " valid records");
        return new ValidationResult(validRecords, errors);
    }

    private List<String> validateRecord(DataRecord record) {
        List<String> errors = new ArrayList<>();

        if (record.getId() == null || record.getId().isEmpty()) {
            errors.add("Record missing ID");
        }

        if (record.getName() == null || record.getName().isEmpty()) {
            errors.add("Record " + record.getId() + " missing name");
        }

        return errors;
    }
}
