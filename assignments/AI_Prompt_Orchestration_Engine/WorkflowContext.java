package aipromptorchestration;

import java.util.ArrayList;
import java.util.List;

public class WorkflowContext {
	private String data;
	private final List<String> traceLogs;

	public WorkflowContext(String initialData) {
		this.data = initialData;
		this.traceLogs = new ArrayList<>();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<String> getTraceLogs() {
		return traceLogs;
	}

	public void addLog(String logMessage) {
		traceLogs.add(logMessage);
	}
}
