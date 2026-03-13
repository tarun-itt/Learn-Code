package interfaces;

import model.DataRecord;
import java.util.List;

public interface DataExporter {
    String getFormat();
    void export(List<DataRecord> records, String filePath);
}
