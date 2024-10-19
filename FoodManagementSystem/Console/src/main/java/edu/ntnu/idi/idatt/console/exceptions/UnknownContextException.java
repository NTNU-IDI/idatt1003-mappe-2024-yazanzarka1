package edu.ntnu.idi.idatt.console.exceptions;

/**
 * Exception thrown when the context is unknown.
 */
public class UnknownContextException extends RuntimeException {

  /**
   * Constructor for the exception.
   *
   * @param message Message to be displayed when exception is thrown.
   */
  public UnknownContextException(String message) {
    super(message);
  }


}
