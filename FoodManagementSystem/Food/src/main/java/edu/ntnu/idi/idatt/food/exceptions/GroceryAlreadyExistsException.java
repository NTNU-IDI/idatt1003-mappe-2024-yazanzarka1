package edu.ntnu.idi.idatt.food.exceptions;

public class GroceryAlreadyExistsException extends RuntimeException {

  public GroceryAlreadyExistsException(String message) {
    super(message);
  }
}
