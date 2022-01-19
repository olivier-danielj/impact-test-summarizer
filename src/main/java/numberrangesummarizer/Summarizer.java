package numberrangesummarizer;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Daniel Olivier
 *
 * Assumptions (reflected in tests):
 * ============
 * 1. Input
 * 	  a. Integers can be any size.
 * 	  b. Input is not necessarily sorted.
 * 	  c. Input shouldn't be empty.
 * 	  d. Sample input provided missed a closing quotation mark, assuming this was a typo.
 *
 * 2. Best summary
 * 	  Input should be summarized as if sorted
 * 	  (e.g. for input "1,2,3,1,2,3 summary is "1-3" not "1-3, 1-3").
 * ============
 *
 * Notes:
 * ============
 *
 * - Some input validation was included anyway for robustness.
 *
 * - I must admit, I'm a bit unfamiliar with all of the stream operations performed.
 *   However, in the spirit of exploring Java 8 I've kept this over older practices.
 *
 * - If input is guarunteed to be sorted we can drop summary time complexity to O(n)
 *
 * ============
 */

public class Summarizer implements NumberRangeSummarizer {

	/**
	 * Collects string of comma separated integers into collection.
	 *
	 * @param input	String of comma separated integers, e.g. "1,2,3".
	 * @return	Collection of Integers, e.g. [1, 2, 3].
	 */
	@Override
	public Collection<Integer> collect(String input) {

		// Check input cleanliness
		Pattern validPattern = Pattern.compile("^-?\\d+(?:,-?\\d+)*$");
		Matcher matcher = validPattern.matcher(input);
		boolean valid = matcher.find();
		if (!valid) {
			throw new IllegalArgumentException("Input must be a comma delimited list of numbers.");
		}

		// Input type conversion
		String[] splitInput = input.split(",");
		Integer[] numbers = Arrays.stream(splitInput).map(Integer::valueOf).toArray(Integer[]::new);
		return Arrays.asList(numbers);
	}

	/**
	 * Summarize collection of integers as a string by collapsing ranges of consecutive numbers.
	 * e.g. [1, 2, 3, 4, 5, 7] becomes "1-5, 7"
	 *
	 * Estimated time complexity ~ O(n log n).
	 *
	 * @param input	Collection of Integers to summarize.
	 * @return	String representation of collection in summarized form.
	 */
	@Override
	public String summarizeCollection(Collection<Integer> input) {

		// Ignore empty requests
		if (input.isEmpty())
			return "";

		// Clean input - Sort and remove duplicates
		Integer[] numbers = input.stream()
				.distinct()
				.sorted()
				.toArray(Integer[]::new);

		// Initialization
		String out = numbers[0].toString();
		Integer prev = numbers[0];
		boolean ranging = false;

		// Summarize (~O(n))
		for (int i = 1; i < numbers.length; i++) {
			int current = numbers[i];

			// Catch non-sequential values
			if (current != prev + 1) {

				// Close ranges
				if (ranging)
					out += "-" + prev;

				out += ", " + current;
				ranging = false;
			} else {
				// Open ranges
				ranging = true;
			}

			prev = current;
		}

		// Close any trailing range
		if (ranging)
			out += "-" + prev;

		return out;
	}

}
