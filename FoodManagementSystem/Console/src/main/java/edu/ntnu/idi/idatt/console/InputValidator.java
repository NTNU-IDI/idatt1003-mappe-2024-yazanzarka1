package edu.ntnu.idi.idatt.console;

/**
 * Interface for input validation.
 *
 * @param <T> type of input to validate
 * @author yazanzarka
 * @see InputHandler
 * @since 0.0.5
 */
public interface InputValidator<T> {

  /**
   * Validate input.
   *
   * @param input input to validate
   * @return true if input is valid, false otherwise
   */
  boolean validateInput(T input);

  /**
   * Get error message.
   *
   * @return error message
   */
  String getErrorMessage();

}
