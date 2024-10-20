package edu.ntnu.idi.idatt.console.exceptions;

/**
 * Exception thrown when an unknown command is given.
 */
public class UnknownCommandException extends RuntimeException {

  /**
   * UnknownCommandException constructor.
   *
   * @param message Message to display when exception is thrown.
   */
  public UnknownCommandException(String message) {
    super(message);
  }
}
