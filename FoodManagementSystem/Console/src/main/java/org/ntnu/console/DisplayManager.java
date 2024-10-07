package org.ntnu.console;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import java.util.List;
import java.util.regex.Pattern;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

/**
 * DisplayManager is responsible for printing data to the console. whether it's a colored text,
 * tables or a simple println. DisplayManager makes modifying data output to the console easier by
 * being the standard way this application outputs data to the console.
 */
public class DisplayManager {

	private static final Pattern ANSI_COLOR_PATTERN = Pattern.compile("\\u001B\\[[;\\d]*m");

	public DisplayManager() {
		AnsiConsole.systemInstall();
	}

	/*
	 * Method for displaying colored messages
	 */
	public void showColoredMessage(String message, Ansi.Color color) {
		System.out.println(Ansi.ansi().fg(color).a(message).reset());
	}

	/*
	 * simplest wrapper for System.out.println
	 */
	public void showMessage(String message) {
		System.out.println(message);
	}

	/*
	 * simplest wrapper for System.out.println
	 */
	public void showSameLineMessage(String message) {
		System.out.print(message);
	}

	/*
	 * simplest wrapper for System.out.println
	 */
	public void showSpace() {
		System.out.println();
	}

	/*
	 * Print tables with headers and rows
	 */

	public void printTable(List<String> headers, List<List<String>> rows) {
		// Calculate the width of each column
		int[] columnWidths = new int[headers.size()];
		for (int i = 0; i < headers.size(); i++) {
			columnWidths[i] = stripAnsiCodes(headers.get(i)).length();
		}

		for (List<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				columnWidths[i] = Math.max(columnWidths[i], stripAnsiCodes(row.get(i)).length());
			}
		}

		// Print the headers
		printRow(headers, columnWidths, true);

		// Print the rows
		for (List<String> row : rows) {
			printRow(row, columnWidths, false);
		}
	}

	private void printRow(List<String> row, int[] columnWidths, boolean isHeader) {
		StringBuilder rowOutput = new StringBuilder("| ");
		for (int i = 0; i < row.size(); i++) {
			rowOutput.append(padRight(row.get(i), columnWidths[i])).append(" | ");
		}
		showColoredMessage(String.valueOf(rowOutput), isHeader ? GREEN : WHITE);

		// Print a separator after the header
		if (isHeader) {
			StringBuilder separator = new StringBuilder("+");
			for (int columnWidth : columnWidths) {
				separator.append("-".repeat(columnWidth + 2)).append("+");
			}
			System.out.println(separator);
		}
	}

	// Strip ANSI color codes for width calculations
	private String stripAnsiCodes(String input) {
		return ANSI_COLOR_PATTERN.matcher(input).replaceAll("");
	}

	private String padRight(String text, int length) {
		return String.format("%-" + length + "s", text);
	}

	// Fancy styled message
	public void showFancyMessage(String message) {
		System.out.println(Ansi.ansi().bg(BLUE).fg(WHITE).a("**** ").reset()
				.fg(YELLOW).a(message).reset().bg(BLUE)
				.fg(WHITE).a(" ****").reset());
	}

	// Cleanup Jansi
	public void shutdown() {
		AnsiConsole.systemUninstall();
	}
}
