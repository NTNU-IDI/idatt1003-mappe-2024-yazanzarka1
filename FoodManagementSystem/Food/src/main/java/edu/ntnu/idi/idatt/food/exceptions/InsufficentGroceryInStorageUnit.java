package edu.ntnu.idi.idatt.food.exceptions;

/**
 * Exception thrown when there is not enough grocery in a storage unit.
 */
public class InsufficentGroceryInStorageUnit extends RuntimeException {

  /**
   * Create an exception with a message.
   *
   * @param message Message to be displayed when exception is thrown.
   */
  public InsufficentGroceryInStorageUnit(String message) {
    super(message);
  }
}
