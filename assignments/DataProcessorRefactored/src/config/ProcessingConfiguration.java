package config;

public class ProcessingConfiguration {
    private final String dateFormat;
    private final int batchSize;
    private final boolean shouldValidate;
    private final boolean shouldTransform;

    public ProcessingConfiguration(String dateFormat, int batchSize,
                                   boolean shouldValidate, boolean shouldTransform) {
        this.dateFormat = dateFormat;
        this.batchSize = batchSize;
        this.shouldValidate = shouldValidate;
        this.shouldTransform = shouldTransform;
    }

    public ProcessingConfiguration() {
        this("yyyy-MM-dd", 100, true, true);
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public boolean shouldValidate() {
        return shouldValidate;
    }

    public boolean shouldTransform() {
        return shouldTransform;
    }
}
