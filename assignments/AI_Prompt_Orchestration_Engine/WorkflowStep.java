package aipromptorchestration;

public interface WorkflowStep {
	WorkflowContext execute(WorkflowContext context) throws StepExecutionException;
}

interface Condition {
	boolean evaluate(WorkflowContext context);
}

class StepExecutionException extends RuntimeException {
	public StepExecutionException(String message) {
		super(message);
	}

	public StepExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
