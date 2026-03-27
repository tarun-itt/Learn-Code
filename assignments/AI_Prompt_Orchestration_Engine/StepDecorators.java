package aipromptorchestration;

class LoggingDecorator implements WorkflowStep {
	private final WorkflowStep wrappedStep;
	private final String stepName;

	LoggingDecorator(WorkflowStep wrappedStep, String stepName) {
		this.wrappedStep = wrappedStep;
		this.stepName = stepName;
	}

	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		String enterLog = "Entering " + stepName + " with data: " + context.getData();
		context.addLog(enterLog);
		System.out.println(enterLog);

		try {
			WorkflowContext result = wrappedStep.execute(context);
			String exitLog = "Exiting " + stepName + " with data: " + result.getData();
			result.addLog(exitLog);
			System.out.println(exitLog);
			return result;
		} catch (StepExecutionException exception) {
			String errorLog = "Failed " + stepName + " with error: " + exception.getMessage();
			context.addLog(errorLog);
			System.err.println(errorLog);
			throw exception;
		}
	}
}

class RetryDecorator implements WorkflowStep {
	private final WorkflowStep wrappedStep;
	private final int maxRetries;

	RetryDecorator(WorkflowStep wrappedStep, int maxRetries) {
		this.wrappedStep = wrappedStep;
		this.maxRetries = maxRetries;
	}

	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		int attempts = 0;
		Exception lastException = null;

		while (attempts < maxRetries) {
			try {
				return wrappedStep.execute(context);
			} catch (StepExecutionException exception) {
				lastException = exception;
				attempts++;
				String retryLog = "Retry attempt " + attempts + " for step.";
				context.addLog(retryLog);
				System.out.println(retryLog);
			}
		}

		throw new StepExecutionException("Step failed after " + maxRetries + " retries.", lastException);
	}
}

class FallbackDecorator implements WorkflowStep {
	private final WorkflowStep primaryStep;
	private final WorkflowStep fallbackStep;

	FallbackDecorator(WorkflowStep primaryStep, WorkflowStep fallbackStep) {
		this.primaryStep = primaryStep;
		this.fallbackStep = fallbackStep;
	}

	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		try {
			return primaryStep.execute(context);
		} catch (StepExecutionException exception) {
			String fallbackLog = "Primary step failed, executing fallback.";
			context.addLog(fallbackLog);
			System.out.println(fallbackLog);
			return fallbackStep.execute(context);
		}
	}
}

class ConditionalDecorator implements WorkflowStep {
	private final WorkflowStep wrappedStep;
	private final Condition condition;

	ConditionalDecorator(WorkflowStep wrappedStep, Condition condition) {
		this.wrappedStep = wrappedStep;
		this.condition = condition;
	}

	@Override
	public WorkflowContext execute(WorkflowContext context) throws StepExecutionException {
		if (condition.evaluate(context)) {
			String condLog = "Condition met, running step.";
			context.addLog(condLog);
			System.out.println(condLog);
			return wrappedStep.execute(context);
		}

		String condLog = "Condition not met, skipping step.";
		context.addLog(condLog);
		System.out.println(condLog);
		return context;
	}
}
