package edu.ntnu.idi.idatt.food.exceptions;

/**
 * Exception thrown when validation of required fields fails.
 */
public class ValidationException extends RuntimeException {

  /**
   * Create a new instance of ValidationException.
   *
   * @param message the message to display
   */
  public ValidationException(String message) {
    super(message);
  }
}
