package interfaces;

import model.DataRecord;
import java.util.List;

public class ReadResult {
    private final List<DataRecord> records;
    private final List<String> errors;

    public ReadResult(List<DataRecord> records, List<String> errors) {
        this.records = records;
        this.errors = errors;
    }

    public List<DataRecord> getRecords() {
        return records;
    }

    public List<String> getErrors() {
        return errors;
    }
}
