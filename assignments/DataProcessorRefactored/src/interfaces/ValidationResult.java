package interfaces;

import model.DataRecord;
import java.util.List;

public class ValidationResult {
    private final List<DataRecord> validRecords;
    private final List<String> errors;

    public ValidationResult(List<DataRecord> validRecords, List<String> errors) {
        this.validRecords = validRecords;
        this.errors = errors;
    }

    public List<DataRecord> getValidRecords() {
        return validRecords;
    }

    public List<String> getErrors() {
        return errors;
    }
}
