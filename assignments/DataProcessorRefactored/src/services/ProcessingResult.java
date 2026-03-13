package services;

import model.DataRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessingResult {
    private final List<DataRecord> records;
    private final ProcessingStatistics statistics;
    private final List<String> errors;

    public ProcessingResult(List<DataRecord> records, ProcessingStatistics statistics,
                            List<String> errors) {
        this.records = Collections.unmodifiableList(records);
        this.statistics = statistics;
        this.errors = Collections.unmodifiableList(new ArrayList<>(errors));
    }

    public List<DataRecord> getRecords() {
        return records;
    }

    public ProcessingStatistics getStatistics() {
        return statistics;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getRecordsProcessed() {
        return records.size();
    }

    public int getErrorCount() {
        return errors.size();
    }
}
