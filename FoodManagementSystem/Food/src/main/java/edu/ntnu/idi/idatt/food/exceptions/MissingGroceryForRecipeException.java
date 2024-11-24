package edu.ntnu.idi.idatt.food.exceptions;

import edu.ntnu.idi.idatt.food.RecipeGrocery;
import java.util.List;

/**
 * Exception thrown when a grocery is missing in storage when a recipe is cooked.
 */
public class MissingGroceryForRecipeException extends RuntimeException {

  List<RecipeGrocery> groceries;

  /**
   * Create a new MissingGroceryForRecipe exception.
   *
   * @param message the message to display
   * @param groceries the groceries that are missing
   */
  public MissingGroceryForRecipeException(String message, List<RecipeGrocery> groceries) {
    super(message);
    this.groceries = groceries;
  }

  /**
   * Get the groceries that are missing.
   *
   * @return List of groceries that are missing
   */
  public List<RecipeGrocery> getGroceries() {
    return groceries;
  }
}
