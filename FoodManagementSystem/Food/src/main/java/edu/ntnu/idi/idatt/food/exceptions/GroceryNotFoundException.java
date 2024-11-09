package edu.ntnu.idi.idatt.food.exceptions;

public class GroceryNotFoundException extends RuntimeException {

  public GroceryNotFoundException(String message) {
    super(message);
  }
}
