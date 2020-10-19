package Assignment1;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	static final int MAX_NUMBER_OF_ELEMENTS = 10;

	PrintStream out;

	Main() {
		out = new PrintStream(System.out);
	}

	boolean askSet(Scanner input, String question, Set set) {
		do {
			out.printf("%s", question);
			if (!input.hasNextLine()) {
				out.printf("\n"); 
				return false;
			}
		} while (!inputContainsCorrectSet(input, set));
		return true;
	}

	boolean askBothSets(Scanner input, Set set1, Set set2) {
		return askSet(input, "Give first set : ", set1) && askSet(input, "Give second set : ", set2);
	}

	private boolean inputContainsCorrectSet(Scanner input, Set set) {
		Scanner lineScanner = new Scanner(input.nextLine());
		lineScanner.useDelimiter("");
		int count = 0;

		// Check if the set is opened correctly.
		if (!nextCharIs(lineScanner, '{')) {
			set.init();
			System.out.printf("Sets should open with '{' \n");
			return false;
		}
		nextChar(lineScanner);

		while (nextCharIsLetter(lineScanner) || nextCharIsDigit(lineScanner) || nextCharIs(lineScanner, ' ')) {
			// Check if the set has been closed correctly.
			if (nextCharIs(lineScanner, ' ')) {
				nextChar(lineScanner);
			}

			Identifier identifier = readIdentifier(lineScanner);
			if (identifier == null) {
				set.init();
				out.printf("Identifiers should begin with a letter.\n");
				return false;
			}
			set.add(identifier);
			count++;

			if (count > MAX_NUMBER_OF_ELEMENTS) {
				out.printf("Maximum input of 10 identifiers exceeded. \n");
				return false;
			}
		}

		if (!nextCharIs(lineScanner, '}')) {
			set.init();
			out.printf("Sets should close with '}' \n");
			return false;
		}

		nextChar(lineScanner);
		if (lineScanner.hasNext()) {
			set.init();
			out.printf("No input after closing the set \n");
			return false;
		}

		return true;
	}

	private Identifier readIdentifier(Scanner input) {
		if (!nextCharIsLetter(input)) {
			return null;
		}

		Identifier identifier = new Identifier(nextChar(input));
		while (nextCharIsDigit(input) || nextCharIsLetter(input)) {
			identifier.add(nextChar(input));
		}

		return identifier;
	}

	char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	private String setToString(SetInterface set) {
		if (set.isEmpty()) {
			return "";
		}

		IdentifierInterface[] identifiers = set.toArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < identifiers.length - 1; i++) {
			sb.append(identifiers[i].getString());
			sb.append(" ");
		}
		sb.append(identifiers[identifiers.length - 1].getString());
		return sb.toString();
	}

	private void calculateAndGiveOutput(Set set1, Set set2) {
		SetInterface difference = set1.difference(set2);
		SetInterface intersection = set1.intersection(set2);
		;
		SetInterface union = set1.union(set2);
		SetInterface symmetricDifference = set1.symmetricDifference(set2);

		out.printf("The difference is: %s\n", setToString(difference));
		out.printf("The intersection is: %s\n", setToString(intersection));
		out.printf("The union is: %s\n", setToString(union));
		out.printf("The symmetric difference is: %s\n", setToString(symmetricDifference));
	}

	void start() {
		Scanner in = new Scanner(System.in);
		Set set1 = new Set(), set2 = new Set();

		while (askBothSets(in, set1, set2)) {
			calculateAndGiveOutput(set1, set2);
		}
	}

	public static void main(String[] argv) {
		new Main().start();
	}

}
