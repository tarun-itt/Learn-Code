package aipromptorchestration;

class GenerateStep implements WorkflowStep {
	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		if (context.getData() == null || context.getData().trim().isEmpty()) {
			throw new StepExecutionException("Generated input cannot be null or empty.");
		}

		context.setData("Generated: " + context.getData());
		return context;
	}
}

class SummarizeStep implements WorkflowStep {
	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		if (context.getData() == null) {
			throw new StepExecutionException("Input data is null for Summarize logic.");
		}

		context.setData("Summary of: " + context.getData());
		return context;
	}
}

class TranslateStep implements WorkflowStep {
	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		if (context.getData() == null) {
			throw new StepExecutionException("Translation requires valid input data.");
		}

		context.setData("Translated: " + context.getData());
		return context;
	}
}
