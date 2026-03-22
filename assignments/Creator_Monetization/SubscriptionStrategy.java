public class SubscriptionStrategy implements EarningStrategy {

	private static final double MINIMUM_PRICE = 0.0;

	private final int subscribers;
	private final double pricePerSubscriber;

	public SubscriptionStrategy(int subscribers, double pricePerSubscriber) {
		if (subscribers < 0) {
			throw new IllegalArgumentException("Subscribers cannot be negative");
		}
		if (pricePerSubscriber <= MINIMUM_PRICE) {
			throw new IllegalArgumentException("Price per subscriber must be positive");
		}
		this.subscribers = subscribers;
		this.pricePerSubscriber = pricePerSubscriber;
	}

	@Override
	public double calculateEarning() {
		return subscribers * pricePerSubscriber;
	}

	@Override
	public String getDescription() {
		return String.format("Subscriptions: %d subscribers × $%.2f/sub", subscribers, pricePerSubscriber);
	}
}
