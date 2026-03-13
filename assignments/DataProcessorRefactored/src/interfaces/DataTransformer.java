package interfaces;

import model.DataRecord;
import java.util.List;

public interface DataTransformer {
    List<DataRecord> transform(List<DataRecord> records);
}
