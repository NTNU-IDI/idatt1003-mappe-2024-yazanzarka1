package edu.ntnu.idi.idatt.food.exceptions;

/**
 * Exception thrown when a grocery is not found in a storage unit.
 */
public class GroceryNotFoundException extends RuntimeException {

  /**
   * Create a new GroceryNotFoundException with a given message.
   *
   * @param message the message to show
   */
  public GroceryNotFoundException(String message) {
    super(message);
  }
}
