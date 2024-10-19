package edu.ntnu.idi.idatt.console.exceptions;

/**
 * Exception thrown when the user input is invalid.
 */
public class UserInputException extends RuntimeException {

  /**
   * Constructor for the exception.
   *
   * @param message Message to be displayed when exception is thrown.
   */
  public UserInputException(String message) {
    super(message);
  }
}
