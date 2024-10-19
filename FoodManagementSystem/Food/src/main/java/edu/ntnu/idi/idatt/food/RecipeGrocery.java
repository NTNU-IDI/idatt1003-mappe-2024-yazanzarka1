package edu.ntnu.idi.idatt.food;

/**
 * Represents a grocery in a recipe.
 */
public class RecipeGrocery {

  private final Grocery grocery;
  private final float amount;

  /**
   * Create a new RecipeGrocery.
   *
   * @param grocery Grocery to be added to a recipe
   * @param amount Amount of grocery in a recipe
   */
  public RecipeGrocery(Grocery grocery, float amount) {
    this.grocery = grocery;
    this.amount = amount;
  }

  /**
   * Get grocery in a recipe.
   *
   * @return Grocery in a recipe
   */
  public Grocery getGrocery() {
    return grocery;
  }

  /**
   * Get amount of grocery in a recipe.
   *
   * @return Amount of grocery in a recipe
   */
  public float getAmount() {
    return amount;
  }

}
