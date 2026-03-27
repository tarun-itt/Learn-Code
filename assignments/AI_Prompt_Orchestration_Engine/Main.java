package aipromptorchestration;

public class Main {
	public static void main(String[] args) {
		System.out.println("=== AI Prompt Orchestration Engine Demo ===");

		WorkflowStep generate = new GenerateStep();
		WorkflowStep summarize = new SummarizeStep();
		WorkflowStep translate = new TranslateStep();

		WorkflowStep loggingGenerate = new LoggingDecorator(generate, "GenerateStep");
		WorkflowStep loggingSummarize = new LoggingDecorator(summarize, "SummarizeStep");
		WorkflowStep loggingTranslate = new LoggingDecorator(translate, "TranslateStep");

		WorkflowStep reliableSummarize = new RetryDecorator(loggingSummarize, 3);

		WorkflowStep translateFallback = context -> {
			context.setData("Fallback translation for: " + context.getData());
			return context;
		};
		WorkflowStep safeTranslate = new FallbackDecorator(loggingTranslate, translateFallback);

		Condition translateCondition = context ->
			context.getData() != null && context.getData().length() > 10;
		WorkflowStep conditionalTranslate = new ConditionalDecorator(safeTranslate, translateCondition);

		Workflow workflow = new Workflow();
		workflow.addStep(loggingGenerate);
		workflow.addStep(reliableSummarize);
		workflow.addStep(conditionalTranslate);

		WorkflowEngine engine = new WorkflowEngine();

		System.out.println("\n--- Run 1: Normal Execution ---");
		engine.run(workflow, "Hello World from AI");

		System.out.println("\n--- Run 2: Failure Handling Demo ---");
		engine.run(workflow, "");
	}
}
