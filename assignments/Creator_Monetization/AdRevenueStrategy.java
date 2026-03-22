public class AdRevenueStrategy implements EarningStrategy {

	private static final double MINIMUM_RATE = 0.0;

	private final int views;
	private final double ratePerView;

	public AdRevenueStrategy(int views, double ratePerView) {
		if (views < 0) {
			throw new IllegalArgumentException("Views cannot be negative");
		}
		if (ratePerView <= MINIMUM_RATE) {
			throw new IllegalArgumentException("Rate per view must be positive");
		}
		this.views = views;
		this.ratePerView = ratePerView;
	}

	@Override
	public double calculateEarning() {
		return views * ratePerView;
	}

	@Override
	public String getDescription() {
		return String.format("Ad Revenue: %d views × $%.4f/view", views, ratePerView);
	}
}
