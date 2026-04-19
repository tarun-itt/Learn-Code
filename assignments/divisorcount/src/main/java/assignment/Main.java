package assignment;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		if (!scanner.hasNextInt()) {
			return;
		}

		int testCaseCount = scanner.nextInt();
		int[] queries = readQueries(scanner, testCaseCount);
		int maxK = findMaximum(queries);

		DivisorCounter counter = new DivisorCounter(maxK);
		printResults(counter, queries);

		scanner.close();
	}

	private static int[] readQueries(Scanner scanner, int count) {
		int[] queries = new int[count];
		for (int i = 0; i < count; i++) {
			queries[i] = scanner.nextInt();
		}
		return queries;
	}

	private static int findMaximum(int[] values) {
		int max = 0;
		for (int value : values) {
			if (value > max) {
				max = value;
			}
		}
		return max;
	}

	private static void printResults(DivisorCounter counter, int[] queries) {
		for (int query : queries) {
			System.out.println(counter.countValidN(query));
		}
	}
}
