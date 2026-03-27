package aipromptorchestration;

import java.util.ArrayList;
import java.util.List;

public class Workflow {
	private final List<WorkflowStep> steps;

	public Workflow() {
		this.steps = new ArrayList<>();
	}

	public void addStep(WorkflowStep step) {
		this.steps.add(step);
	}

	public List<WorkflowStep> getSteps() {
		return steps;
	}
}

class WorkflowEngine {
	WorkflowContext run(Workflow workflow, String initialInput) {
		WorkflowContext context = new WorkflowContext(initialInput);

		System.out.println("Starting workflow execution...");
		context.addLog("Workflow Started. Initial input: " + initialInput);

		for (WorkflowStep step : workflow.getSteps()) {
			try {
				context = step.execute(context);
			} catch (StepExecutionException exception) {
				String errorLog = "Workflow halted: " + exception.getMessage();
				context.addLog(errorLog);
				System.err.println(errorLog);
				break;
			}
		}

		System.out.println("Workflow finished. Final Output: " + context.getData());
		context.addLog("Workflow Completed. Final Output: " + context.getData());
		return context;
	}
}
