package assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisorCounterTest {

	private static final int PRECOMPUTE_LIMIT = 100;

	private DivisorCounter counter;

	@BeforeEach
	void setUp() {
		counter = new DivisorCounter(PRECOMPUTE_LIMIT);
	}

	@Test
	void shouldReturnTwoForKFifteen() {
		assertEquals(2, counter.countValidN(15));
	}

	@Test
	void shouldReturnOneForKThree() {
		assertEquals(1, counter.countValidN(3));
	}

	@Test
	void shouldReturnCorrectCountForKTen() {
		assertEquals(1, counter.countValidN(10));
	}

	@Test
	void shouldReturnCorrectCountForKTwenty() {
		assertEquals(2, counter.countValidN(20));
	}

	@Test
	void shouldReturnCorrectCountForLargerK() {
		assertEquals(8, counter.countValidN(50));
	}

	@Test
	void shouldReturnZeroForKTwo() {
		assertEquals(0, counter.countValidN(2));
	}

	@Test
	void shouldReturnZeroForKOne() {
		assertEquals(0, counter.countValidN(1));
	}

	@Test
	void shouldReturnZeroForKZero() {
		assertEquals(0, counter.countValidN(0));
	}

	@Test
	void shouldReturnZeroForNegativeK() {
		assertEquals(0, counter.countValidN(-5));
	}

	@Test
	void shouldThrowForKExceedingPrecomputedLimit() {
		DivisorCounter smallCounter = new DivisorCounter(10);
		assertThrows(IllegalArgumentException.class, () -> smallCounter.countValidN(15));
	}

	@Test
	void shouldHandleKAtExactPrecomputedLimit() {
		DivisorCounter exactCounter = new DivisorCounter(15);
		assertEquals(2, exactCounter.countValidN(15));
	}
}
