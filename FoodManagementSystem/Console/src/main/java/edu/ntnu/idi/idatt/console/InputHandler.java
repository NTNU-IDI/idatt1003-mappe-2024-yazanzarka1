package edu.ntnu.idi.idatt.console;

import java.util.Scanner;


/**
 * InputHandler is responsible for waiting for user's input and trimming it so it can be safely
 * passed to commandRegistry for executing commands.
 */
public class InputHandler {

  private final Scanner scanner;

  /**
   * Initiate InputHandler with a scanner instance.
   */
  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  // get input from user
  public String getInput() {
    return getInput("Enter Command: ");
  }

  /**
   * Gets input from user with a prompt.
   *
   * @param prompt prompt to show to user
   * @return String trimmed input from user
   */
  public String getInput(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
  }

  /**
   * Added for mocking user input.
   * Deprecated 09.10.2024
   *
   * @param input mocked input
   * @return String mocked string
   */
  @Deprecated(forRemoval = true)
  public String mockInput(String input) {
    return input.trim();
  }
}
