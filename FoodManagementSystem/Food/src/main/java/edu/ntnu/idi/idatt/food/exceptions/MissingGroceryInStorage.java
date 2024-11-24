package edu.ntnu.idi.idatt.food.exceptions;

/**
 * Exception thrown when a grocery is missing in storage.
 */
public class MissingGroceryInStorage extends RuntimeException {

  /**
   * Create a new MissingGroceryInStorage exception.
   *
   * @param message the message to display
   */
  public MissingGroceryInStorage(String message) {
    super(message);
  }
}
