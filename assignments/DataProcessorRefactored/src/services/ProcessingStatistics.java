package services;

import interfaces.Logger;
import model.DataRecord;

import java.util.List;

public class ProcessingStatistics {
    private final int totalRecords;
    private final int errorCount;
    private final double totalValue;
    private final double averageValue;

    public ProcessingStatistics(int totalRecords, int errorCount,
                                double totalValue, double averageValue) {
        this.totalRecords = totalRecords;
        this.errorCount = errorCount;
        this.totalValue = totalValue;
        this.averageValue = averageValue;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getAverageValue() {
        return averageValue;
    }
}
