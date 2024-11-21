package edu.ntnu.idi.idatt.console;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.CYAN;
import static org.fusesource.jansi.Ansi.Color.WHITE;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import java.util.Arrays;
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

  private static final Ansi.Color HEADER_COLOR = YELLOW;
  private static final Ansi.Color DATA_COLOR = CYAN;
  private static final Pattern ANSI_COLOR_PATTERN = Pattern.compile("\\u001B\\[[;\\d]*m");

  /**
   * Initiate AnsiConsole.
   */
  public DisplayManager() {
    AnsiConsole.systemInstall();
  }

  /**
   * Method for displaying colored messages.
   */
  public void showColoredMessage(String message, Ansi.Color color) {
    System.out.println(Ansi.ansi().fg(color).a(message).reset());
  }

  /**
   * simplest wrapper for System.out.println.
   *
   * @param message message to be shown
   */
  public void showMessage(String message) {
    System.out.println(message);
  }

  /**
   * simplest wrapper for System.out.println.
   *
   * @param message message to be shown
   */
  public void showSameLineMessage(String message) {
    System.out.print(message);
  }

  /**
   * simplest wrapper for System.out.println.
   */
  public void showSpace() {
    System.out.println();
  }

  /**
   * Table printing method.
   *
   * @param tableData table data to be printed
   * @see TableData
   */
  public void printTable(TableData tableData) {
    printTable("", tableData);
  }

  /**
   * . Print a table to the console
   *
   * @param title   title of the table
   * @param tableData table data to be printed.
   * @see TableData
   */
  public void printTable(String title, TableData tableData) {
    // Calculate the width of each column
    int[] columnWidths = new int[tableData.headers().size()];
    for (int i = 0; i < tableData.headers().size(); i++) {
      columnWidths[i] = stripAnsiCodes(tableData.headers().get(i)).length();
    }

    for (List<String> row : tableData.data()) {
      for (int i = 0; i < row.size(); i++) {
        /* The i-th column should be at least as wide
         as the i-th text in the row without ANSI color codes
         */
        columnWidths[i] = Math.max(columnWidths[i], stripAnsiCodes(row.get(i)).length());
      }
    }

    if (!title.isEmpty()) {
      int totalWidth = Arrays.stream(columnWidths).sum();

      // Print the title
      showColoredMessage(
          padLeft(title, (totalWidth + columnWidths.length * 3) / 2 + title.length() / 2), HEADER_COLOR);
    }

    printRow(tableData.headers(), columnWidths, true);
    for (List<String> row : tableData.data()) {
      printRow(row, columnWidths, false);
    }
  }

  private void printRow(List<String> row, int[] columnWidths, boolean isHeader) {
    /* each row starts with a separator | and each column is seperated by |.*/
    StringBuilder rowOutput = new StringBuilder("| ");

    /* for each column in the row, pad the column to the right with spaces to match the column width
     *
     */
    for (int i = 0; i < row.size(); i++) {

      /* strip ANSI color codes for width calculations
       * Get the difference between column text length without ansi codes and with ansi codes
       * then pad the text to the right with the difference to match the column width
       */
      rowOutput.append(padRight(row.get(i),
              columnWidths[i] - (stripAnsiCodes(row.get(i)).length() - row.get(i).length())))
          .append(" | ");
    }
    showColoredMessage(String.valueOf(rowOutput), isHeader ? HEADER_COLOR : DATA_COLOR);

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

  private String padLeft(String text, int length) {
    return String.format("%" + length + "s", text);
  }

  /**
   * Shows a fancy colored message that requires users attention.
   *
   * @param message Message to be shown to user
   */
  public void showFancyMessage(String message) {
    System.out.println(Ansi.ansi().bg(BLUE).fg(WHITE).a("**** ").a(message).a(" ****").reset());
  }

  /**
   * gracefully clean system.
   */
  public void shutdown() {
    AnsiConsole.systemUninstall();
  }
}
