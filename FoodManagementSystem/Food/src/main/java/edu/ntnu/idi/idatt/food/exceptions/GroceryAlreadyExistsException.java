package edu.ntnu.idi.idatt.food.exceptions;

/**
 * Exception thrown when a grocery already exists in a storage unit.
 */
public class GroceryAlreadyExistsException extends RuntimeException {

  /**
   * Create a new GroceryAlreadyExistsException with a given message.
   *
   * @param message the message to show
   */
  public GroceryAlreadyExistsException(String message) {
    super(message);
  }
}
