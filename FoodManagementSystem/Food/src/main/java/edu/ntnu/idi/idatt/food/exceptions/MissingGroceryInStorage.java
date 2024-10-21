package edu.ntnu.idi.idatt.food.exceptions;

public class MissingGroceryInStorage extends RuntimeException {

  public MissingGroceryInStorage(String message) {
    super(message);
  }
}
