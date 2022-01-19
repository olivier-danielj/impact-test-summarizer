package numberrangesummarizer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Daniel Olivier
 *
 * Notes:
 * ============
 * - Not sure what Impact test naming convention is, but I've gone for a method_circumstance_expectation format.
 * - I've avoided documentation here because it seems like overkill, but I understand that there may be cases where
 *   it would be useful.
 * - Since this is a fairly simple interface. I've hardcoded a few of the tests to avoid recreating the implementation.
 * ============
 */

public class SummarizerTest {

	Summarizer summary;

	@BeforeEach
	public void setUp() {
		summary = new Summarizer();
	}

	/* Test collect() */

	@Test
	void collect_StandardInput_ValidCollection() {
		Integer[] expected = {1,3,4,5,6,7,10};
		Assert.assertArrayEquals(expected, summary.collect("1,3,4,5,6,7,10").toArray());
	}

	@Test
	void collect_ExtremeValues_ValidCollection() {
		Integer[] expected = {Integer.MIN_VALUE, 1, 3, -42, Integer.MAX_VALUE};
		Assert.assertArrayEquals(expected, summary.collect("-2147483648,1,3,-42,2147483647").toArray());
	}

	@Test
	void collect_SingleInput_ValidCollection() {
		Integer[] expected = {42};
		Assert.assertArrayEquals(expected, summary.collect("42").toArray());
	}

	@Test
	void collect_EmptyInput_ExceptionThrown() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> summary.collect(""));
		Assert.assertEquals("Input must be a comma delimited list of numbers.", exception.getMessage());
	}

	@Test
	void collect_AlphabetInput_ExceptionThrown() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> summary.collect("1,a,2"));
		Assert.assertEquals("Input must be a comma delimited list of numbers.", exception.getMessage());
	}

	@Test
	void collect_BadFormatInput_ExceptionThrown() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> summary.collect("1,,2,3"));
		Assert.assertEquals("Input must be a comma delimited list of numbers.", exception.getMessage());
	}

	/* Test summarizeCollection() */

	@Test
	void summarizeCollection_StandardInput_ValidSummary() {
		Integer[] input = {1,3,6,7,8,12,13,14,15,21,22,23,24,31};
		Assert.assertEquals("1, 3, 6-8, 12-15, 21-24, 31", summary.summarizeCollection(Arrays.asList(input)));
	}

	@Test
	void summarizeCollection_ExtremeInput_ValidSummary() {
		Integer[] input = {Integer.MIN_VALUE, 1, 2, 3, Integer.MAX_VALUE};
		Assert.assertEquals("-2147483648, 1-3, 2147483647", summary.summarizeCollection(Arrays.asList(input)));
	}

	@Test
	void summarizeCollection_DuplicateInput_ValidSummary() {
		Integer[] input = {1,1,1,2,3,5,5,42};
		Assert.assertEquals("1-3, 5, 42", summary.summarizeCollection(Arrays.asList(input)));
	}

	@Test
	void summarizeCollection_UnsortedInput_ValidSummary() {
		Integer[] input = {44,3,42,1,43,2,7};
		Assert.assertEquals("1-3, 7, 42-44", summary.summarizeCollection(Arrays.asList(input)));
	}

	@Test
	void summarizeCollection_SingleInput_ValidSummary() {
		Integer[] input = {42};
		Assert.assertEquals("42", summary.summarizeCollection(Arrays.asList(input)));
	}

	@Test
	void summarizeCollection_EmptyInput_EmptySummary() {
		Integer[] input = {};
		Assert.assertEquals("", summary.summarizeCollection(Arrays.asList(input)));
	}

}
