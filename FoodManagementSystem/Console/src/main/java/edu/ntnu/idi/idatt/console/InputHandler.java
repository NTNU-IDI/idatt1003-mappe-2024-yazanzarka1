package edu.ntnu.idi.idatt.console;

import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.fusesource.jansi.Ansi.Color;


/**
 * InputHandler is responsible for waiting for user's input and trimming it so it can be safely
 * passed to commandRegistry for executing commands.
 *
 * @author yazanzarka
 * @since 0.0.1
 */
public class InputHandler {

  public static final String CANCEL_WORD = "cancel";
  private final Scanner scanner;
  private final DisplayManager displayManager;

  /**
   * Initiate InputHandler with a scanner instance.
   */
  public InputHandler(DisplayManager displayManager) {
    this.scanner = new Scanner(System.in);
    this.displayManager = displayManager;
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
   * Gets input from user with a prompt and a validator.
   *
   * @param prompt    prompt to show to user
   * @param validator validator to validate input
   * @return String trimmed input from user
   */
  public String getString(String prompt, InputValidator<String> validator) {
    String input = null;
    while (input == null) {
      String value = getInput(prompt);
      if (validator.validateInput(value)) {
        input = value;
      } else {
        displayManager.showColoredMessage(validator.getErrorMessage(), Color.RED);
      }
    }
    if (input.equals(CANCEL_WORD)) {
      throw new UserInputException("User cancelled operation");
    }
    return input.trim();
  }

  /**
   * Gets input from user with a prompt and a validator.
   *
   * @param prompt    prompt to show to user
   * @param validator validator to validate input
   * @return String trimmed input from user
   */
  public Float getFloat(String prompt, InputValidator<Float> validator) {
    Float input = null;
    while (input == null) {
      String value = getString(prompt,
          new StringValidator("Invalid input. This field cannot be empty.", 1, 10));
      try {
        input = Float.parseFloat(value);
      } catch (NumberFormatException e) {
        displayManager.showColoredMessage("Invalid input. Please enter a number.", Color.RED);
      }
      if (!validator.validateInput(input)) {
        input = null;
        displayManager.showColoredMessage(validator.getErrorMessage(), Color.RED);
      }
    }
    return input;
  }


  /**
   * Gets input from user with a prompt and a validator.
   *
   * @param prompt    prompt to show to user
   * @param validator validator to validate input
   * @return String trimmed input from user
   */
  public int getInt(String prompt, InputValidator<Integer> validator) {
    Integer input = null;
    while (input == null) {
      String value = getString(prompt,
          new StringValidator("Invalid input. This field cannot be empty.", 1, 10));
      try {
        input = Integer.parseInt(value);
      } catch (NumberFormatException e) {
        displayManager.showColoredMessage("Invalid input. Please enter a number.", Color.RED);
      }
      if (!validator.validateInput(input)) {
        input = null;
        displayManager.showColoredMessage(validator.getErrorMessage(), Color.RED);
      }
    }
    return input;
  }

  /**
   * Gets input from user with a prompt and a validator.
   *
   * @param prompt    prompt to show to user
   * @param validator validator to validate input
   * @return String trimmed input from user
   */
  public Date getDate(String prompt, InputValidator<Date> validator) {
    Date input = null;
    while (input == null) {
      String value = getString(prompt,
          new StringValidator("Invalid input. This field cannot be empty.", 1, 10));
      try {
        input = new SimpleDateFormat("dd.MM.yyyy").parse(value);
      } catch (ParseException e) {
        displayManager.showColoredMessage(
            "Invalid input. Please enter a date in the format dd.MM.yyyy.", Color.RED);
      }
      if (!validator.validateInput(input)) {
        input = null;
        displayManager.showColoredMessage(validator.getErrorMessage(), Color.RED);
      }
    }
    return input;
  }

}
