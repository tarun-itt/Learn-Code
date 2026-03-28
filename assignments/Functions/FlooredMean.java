import java.util.*;

public class FlooredMean {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int elementCount = scanner.nextInt();
        int queryCount = scanner.nextInt();

        long[] values = readArray(scanner, elementCount);
        long[] prefixSum = buildPrefixSum(values);

        processQueries(scanner, prefixSum, queryCount);
    }

    private static long[] readArray(Scanner scanner, int elementCount) {
        long[] array = new long[elementCount + 1];
        for (int index = 1; index <= elementCount; index++) {
            array[index] = scanner.nextLong();
        }
        return array;
    }

    private static long[] buildPrefixSum(long[] array) {
        long[] prefixSum = new long[array.length];
        for (int index = 1; index < array.length; index++) {
            prefixSum[index] = prefixSum[index - 1] + array[index];
        }
        return prefixSum;
    }

    private static void processQueries(
            Scanner scanner,
            long[] prefixSum,
            int queryCount
    ) {
        while (queryCount-- > 0) {
            int leftIndex = scanner.nextInt();
            int rightIndex = scanner.nextInt();

            long sum = prefixSum[rightIndex] - prefixSum[leftIndex - 1];
            int count = rightIndex - leftIndex + 1;

            System.out.println(sum / count);
        }
    }
}