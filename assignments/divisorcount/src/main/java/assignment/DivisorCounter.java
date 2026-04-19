package assignment;

public class DivisorCounter {

	private static final int DEFAULT_LIMIT = 10;

	private final int[] divisorCounts;
	private final int[] validPairCounts;

	public DivisorCounter(int maxK) {
		if (maxK <= 0) {
			maxK = DEFAULT_LIMIT;
		}

		divisorCounts = buildDivisorSieve(maxK);
		validPairCounts = buildPrefixSums(maxK);
	}

	public int countValidN(int k) {
		if (k >= validPairCounts.length) {
			throw new IllegalArgumentException(
				"k exceeds maximum precomputed limit: " + (validPairCounts.length - 1)
			);
		}
		if (k <= 2) {
			return 0;
		}
		return validPairCounts[k - 1];
	}

	private int[] buildDivisorSieve(int limit) {
		int[] sieve = new int[limit + 2];
		for (int i = 1; i <= limit + 1; i++) {
			for (int multiple = i; multiple <= limit + 1; multiple += i) {
				sieve[multiple]++;
			}
		}
		return sieve;
	}

	private int[] buildPrefixSums(int limit) {
		int[] prefixSums = new int[limit + 1];
		int runningCount = 0;

		for (int n = 2; n <= limit; n++) {
			if (hasSameDivisorCountAsNext(n)) {
				runningCount++;
			}
			prefixSums[n] = runningCount;
		}
		return prefixSums;
	}

	private boolean hasSameDivisorCountAsNext(int n) {
		return divisorCounts[n] == divisorCounts[n + 1];
	}
}
