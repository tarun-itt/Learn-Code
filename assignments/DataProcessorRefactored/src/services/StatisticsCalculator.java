package services;

import interfaces.Logger;
import model.DataRecord;

import java.util.List;

public class StatisticsCalculator {
    private final Logger logger;

    public StatisticsCalculator(Logger logger) {
        this.logger = logger;
    }

    public ProcessingStatistics calculate(List<DataRecord> records, int errorCount) {
        logger.log("Calculating statistics...");

        double totalValue = 0;
        for (DataRecord record : records) {
            totalValue += record.getValue();
        }

        double averageValue = records.isEmpty() ? 0 : totalValue / records.size();

        ProcessingStatistics stats = new ProcessingStatistics(
                records.size(), errorCount, totalValue, averageValue
        );

        logger.log("Statistics calculated: TotalRecords=" + stats.getTotalRecords()
                + ", TotalValue=" + String.format("%.2f", stats.getTotalValue()));
        return stats;
    }
}
