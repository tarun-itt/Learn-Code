public class Program {

	public static void main(String[] args) {
		Creator techInfluencer = buildTechInfluencer();
		Creator fitnessStreamer = buildFitnessStreamer();
		Creator podcastHost = buildPodcastHost();

		printBreakdown(techInfluencer);
		printBreakdown(fitnessStreamer);
		printBreakdown(podcastHost);

		demonstrateRuntimeExtensibility(fitnessStreamer);
		demonstrateRegionalRateAdjustment();
	}

	private static Creator buildTechInfluencer() {
		Creator creator = new Creator("Alex (Tech Influencer)");
		creator.addEarningStrategy(new AdRevenueStrategy(500_000, 0.05));
		creator.addEarningStrategy(new SubscriptionStrategy(10_000, 2.00));
		creator.addEarningStrategy(new BrandDealStrategy(50_000, 0.85));
		return creator;
	}

	private static Creator buildFitnessStreamer() {
		Creator creator = new Creator("Jordan (Fitness Streamer)");
		creator.addEarningStrategy(new AdRevenueStrategy(200_000, 0.03));
		creator.addEarningStrategy(new LiveGiftStrategy(1_500, 3.50));
		creator.addEarningStrategy(new SubscriptionStrategy(5_000, 4.99));
		return creator;
	}

	private static Creator buildPodcastHost() {
		Creator creator = new Creator("Sam (Podcast Host)");
		creator.addEarningStrategy(new BrandDealStrategy(30_000, 0.70));
		creator.addEarningStrategy(new SubscriptionStrategy(8_000, 1.50));
		return creator;
	}

	private static void demonstrateRuntimeExtensibility(Creator creator) {
		System.out.println("--- Adding Brand Deal to " + creator.getName() + " at runtime ---");
		creator.addEarningStrategy(new BrandDealStrategy(20_000, 0.60));
		printBreakdown(creator);
	}

	private static void demonstrateRegionalRateAdjustment() {
		double baseRate = 0.05;
		double indiaRegionMultiplier = 0.4;
		double holidaySeasonMultiplier = 1.5;

		double adjustedRate = baseRate * indiaRegionMultiplier * holidaySeasonMultiplier;

		Creator creator = new Creator("Priya (India, Holiday Season)");
		creator.addEarningStrategy(new AdRevenueStrategy(1_000_000, adjustedRate));
		creator.addEarningStrategy(new SubscriptionStrategy(20_000, 1.00));

		System.out.println("--- Region & Season adjusted earnings ---");
		System.out.printf("  Base ad rate: $%.4f → Adjusted (India × Holiday): $%.4f%n%n",
			baseRate, adjustedRate);
		printBreakdown(creator);
	}

	private static void printBreakdown(Creator creator) {
		System.out.println(creator.getEarningBreakdown());
	}
}
