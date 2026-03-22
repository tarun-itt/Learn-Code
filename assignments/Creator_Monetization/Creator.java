import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Creator {

	private static final int BREAKDOWN_COLUMN_WIDTH = 45;

	private final String name;
	private final List<EarningStrategy> earningStrategies;

	public Creator(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Creator name cannot be null or blank");
		}
		this.name = name;
		this.earningStrategies = new ArrayList<>();
	}

	public void addEarningStrategy(EarningStrategy strategy) {
		if (strategy == null) {
			throw new IllegalArgumentException("Earning strategy cannot be null");
		}
		earningStrategies.add(strategy);
	}

	public void removeEarningStrategy(EarningStrategy strategy) {
		earningStrategies.remove(strategy);
	}

	public String getEarningBreakdown() {
		StringBuilder breakdown = new StringBuilder();
		breakdown.append(String.format("Earnings for %s:%n", name));

		for (EarningStrategy strategy : earningStrategies) {
			breakdown.append(formatEarningLine(strategy.getDescription(), strategy.calculateEarning()));
		}

		breakdown.append(formatEarningLine("TOTAL", calculateTotalEarnings()));
		return breakdown.toString();
	}

	public double calculateTotalEarnings() {
		double total = 0;
		for (EarningStrategy strategy : earningStrategies) {
			total += strategy.calculateEarning();
		}
		return total;
	}

	public String getName() {
		return name;
	}

	public List<EarningStrategy> getEarningStrategies() {
		return Collections.unmodifiableList(earningStrategies);
	}

	private String formatEarningLine(String label, double amount) {
		return String.format("  %-" + BREAKDOWN_COLUMN_WIDTH + "s → $%,.2f%n", label, amount);
	}
}
