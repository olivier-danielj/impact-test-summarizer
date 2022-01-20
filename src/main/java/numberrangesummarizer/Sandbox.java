package numberrangesummarizer;

import java.util.Collection;
import java.util.Scanner;

public class Sandbox {

	/**
	 * @author Daniel Olivier
	 *
	 * The Sandbox is used to play around with my implementation of the NumberRangerSummarizer.
	 *
	 * Settings
	 * =========
	 * METHOD 	- Determines the input method, can be set to TERMINAL, ARGUMENT or DEMO (default values).
	 * VERBOSE 	- Determines whether additional terminal output will be displayed. If set to false, only summaries will
	 * 	be displayed.
	 *
	 * Terminal
	 * ========
	 * In terminal mode, you can either enter a comma separated list of integers to see its summary
	 * or type "quit" to exit the program.
	 *
	 */

	enum Mode {
		TERMINAL,
		ARGUMENT,
		DEMO
	}

	private static final Mode MODE = Mode.TERMINAL;
	private static final boolean VERBOSE = true;

	private static final String titleText = "Number Range Summarizer \nby Daniel Olivier\n";

	public static void main(String[] args) {
		switch (MODE) {
			case TERMINAL:
				runTerminal();
				break;
			case ARGUMENT:
				runArgument(args[0]);
				break;
			default:
				runDemo();
				break;
		}
	}

	/**
	 * Handles terminal input for summarizer interaction
	 */
	private static void runTerminal() {
		say(titleText);
		runMenu();
	}

	/**
	 * Summarizes the first command line argument
	 * @param arg	Comma separated list of integer values.
	 */
	private static void runArgument(String arg) {
		summarize(arg);
	}

	/**
	 * Summarizes the example input.
	 */
	private static void runDemo() {
		say("Sample input:\n1,3,6,7,8,12,13,14,15,21,22,23,24,31" + "\n" + "Result:");
		summarize("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
	}

	/**
	 * Displays the terminal mode menu and waits for a command or data.
	 */
	private static void runMenu() {
		String in = prompt("Enter a comma separated list of integers to summarize or \"quit\" to exit:");

		switch (in) {
			case "quit":
				say("Have a nice day!");
				return;
			default:
				summarize(in);
				runMenu();
				break;
		}
	}

	/**
	 * Prompts the user for input data.
	 * @param query	Prompt description to be displayed.
	 * @return	User input response.
	 */
	private static String prompt(String query) {
		say(query);
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}

	/**
	 * Collect and summarize a list of comma separated integers. Displays result.
	 * @param in	List of comma separated integers.
	 */
	private static void summarize(String in) {
		Summarizer summarizer = new Summarizer();

		Collection<Integer> data = summarizer.collect(in);
		String out = summarizer.summarizeCollection(data);
		System.out.println(out);
	}

	/**
	 * Displays output text iff the VERBOSE flag is true.
	 * @param out	Text to display.
	 */
	private static void say(String out) {
		if (VERBOSE)
			System.out.println(out);
	}

}
