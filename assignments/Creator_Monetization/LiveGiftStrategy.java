public class LiveGiftStrategy implements EarningStrategy {

	private static final double MINIMUM_GIFT_VALUE = 0.0;

	private final int giftCount;
	private final double averageGiftValue;

	public LiveGiftStrategy(int giftCount, double averageGiftValue) {
		if (giftCount < 0) {
			throw new IllegalArgumentException("Gift count cannot be negative");
		}
		if (averageGiftValue <= MINIMUM_GIFT_VALUE) {
			throw new IllegalArgumentException("Average gift value must be positive");
		}
		this.giftCount = giftCount;
		this.averageGiftValue = averageGiftValue;
	}

	@Override
	public double calculateEarning() {
		return giftCount * averageGiftValue;
	}

	@Override
	public String getDescription() {
		return String.format("Live Gifts: %d gifts × $%.2f avg", giftCount, averageGiftValue);
	}
}
