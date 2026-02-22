package interfaces;

import model.DataRecord;
import java.util.List;

public interface DataValidator {
    ValidationResult validate(List<DataRecord> records);
}
