public class BrandDealStrategy implements EarningStrategy {

	private static final double MINIMUM_ENGAGEMENT_RATE = 0.0;
	private static final double MAXIMUM_ENGAGEMENT_RATE = 1.0;

	private final double dealAmount;
	private final double engagementRate;

	public BrandDealStrategy(double dealAmount, double engagementRate) {
		if (dealAmount <= 0) {
			throw new IllegalArgumentException("Deal amount must be positive");
		}
		if (engagementRate <= MINIMUM_ENGAGEMENT_RATE || engagementRate > MAXIMUM_ENGAGEMENT_RATE) {
			throw new IllegalArgumentException("Engagement rate must be between 0 (exclusive) and 1 (inclusive)");
		}
		this.dealAmount = dealAmount;
		this.engagementRate = engagementRate;
	}

	@Override
	public double calculateEarning() {
		return dealAmount * engagementRate;
	}

	@Override
	public String getDescription() {
		return String.format("Brand Deal: $%.2f × %.0f%% engagement", dealAmount, engagementRate * 100);
	}
}
