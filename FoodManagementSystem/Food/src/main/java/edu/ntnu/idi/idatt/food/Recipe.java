package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent a recipe in a recipe manager. A recipe has a name, description, groceries, steps and
 * people count. A recipe can be displayed, get the price and get the groceries in a recipe. A
 * recipe is used in RecipeManager. A recipe is immutable.
 *
 * @see RecipeManager
 */
public class Recipe {

  private final String name;
  private final String description;
  private final Map<String, RecipeGrocery> groceries;
  private final int peopleCount;
  private final String steps;


  /**
   * Initiate a recipe.
   *
   * @param name        name of a recipe
   * @param description description of a recipe
   */
  public Recipe(String name, String description) {
    this(name, description, "No Steps Added", 4);

  }

  /**
   * Initiate a recipe.
   *
   * @param name           name of a recipe
   * @param description    description of a recipe
   * @param steps          steps to make a recipe
   * @param peopleCount    number of people the recipe is for
   *
   * @throws IllegalArgumentException if name is null or blank, name length is less than 3 or greater
   * @throws IllegalArgumentException if description is null or blank, description length is less than 10 or greater
   * @throws IllegalArgumentException if steps is null or blank, steps length is less than 10 or greater
   * @throws IllegalArgumentException if peopleCount is less than or equal to 0 or greater than 10
   */
  public Recipe(String name, String description, String steps, int peopleCount) {

    if (name == null || name.isBlank() || name.length() < 3 || name.length() > 50) {
      throw new IllegalArgumentException(
          "Recipe name cannot be null or blank and must be between 3 and 50 characters");
    }
    if (description == null || description.isBlank() || description.length() < 10
        || description.length() > 200) {
      throw new IllegalArgumentException(
          "Recipe description cannot be null or blank and must be between 10 and 200 characters");
    }
    if (steps == null || steps.isBlank() || steps.length() < 10 || steps.length() > 500) {
      throw new IllegalArgumentException(
          "Recipe steps cannot be null or blank and must be between 10 and 500 characters");
    }
    if (peopleCount <= 0 || peopleCount > 10) {
      throw new IllegalArgumentException("People count must be greater than 0 and less than 10");
    }

    this.name = name;
    this.groceries = new HashMap<>();
    this.description = description;
    this.steps = steps;
    this.peopleCount = peopleCount;

  }

  /**
   * Add a grocery to recipe with amount.
   *
   * @param grocery Grocery to be added
   * @param amount  amount of grocery in a recipe
   * @throws NullPointerException if grocery is null
   * @throws IllegalArgumentException if amount is less than or equal to 0
   */
  public void addGrocery(Grocery grocery, float amount) {
    if (grocery == null) {
      throw new NullPointerException("Grocery cannot be null");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0");
    }
    groceries.computeIfPresent(grocery.getGroceryName(), (k, v) -> {
      float newAmount = v.amount() + amount;
      return new RecipeGrocery(grocery, newAmount);
    });
    groceries.putIfAbsent(grocery.getGroceryName(), new RecipeGrocery(grocery, amount));
  }

  /**
   * Get Groceries in a recipe.
   *
   * @return Map of Groceries and value in a recipe
   */
  public Map<String, RecipeGrocery> getGroceries() {
    return groceries;
  }

  /**
   * Get a grocery in a recipe.
   *
   * @param groceryName name of grocery
   * @return RecipeGrocery
   */
  public RecipeGrocery getGrocery(String groceryName) {
    return groceries.get(groceryName);
  }


  /**
   * Get the price of a recipe.
   *
   * @return float price of a recipe
   */
  public float getRecipePrice() {
    float price = 0;
    for (RecipeGrocery entry : groceries.values()) {
      price += entry.amount() * entry.grocery().getPricePerUnit();
    }
    return price;
  }

  /**
   * Get the name of a recipe.
   *
   * @return String name of a recipe
   */
  public String getName() {
    return name;
  }

  /**
   * Get the description of a recipe.
   *
   * @return String description of a recipe
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the steps to make a recipe.
   *
   * @return String steps to make a recipe
   */
  public String getSteps() {
    return steps;
  }

  /**
   * Get the number of people the recipe is for.
   *
   * @return int number of people the recipe is for
   */
  public int getPeopleCount() {
    return peopleCount;
  }


}
