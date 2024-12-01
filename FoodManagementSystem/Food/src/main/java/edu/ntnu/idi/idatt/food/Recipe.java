package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.food.constants.RecipeConstants;
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
   * @param name        name of a recipe
   * @param description description of a recipe
   * @param steps       steps to make a recipe
   * @param peopleCount number of people the recipe is for
   * @throws IllegalArgumentException if name is null or blank, name length is less than 3 or
   *                                  greater
   * @throws IllegalArgumentException if description is null or blank, description length is less
   *                                  than 10 or greater
   * @throws IllegalArgumentException if steps is null or blank, steps length is less than 10 or
   *                                  greater
   * @throws IllegalArgumentException if peopleCount is less than or equal to 0 or greater than 10
   */
  public Recipe(String name, String description, String steps, int peopleCount) {

    if (name == null || name.isBlank() || name.length() < RecipeConstants.MIN_RECIPE_NAME_LENGTH
        || name.length() > RecipeConstants.MAX_RECIPE_NAME_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Recipe name cannot be null or blank and must be between %s and %s characters",
              RecipeConstants.MIN_RECIPE_NAME_LENGTH,
              RecipeConstants.MAX_RECIPE_NAME_LENGTH));
    }
    if (description == null || description.isBlank()
        || description.length() < RecipeConstants.MIN_RECIPE_DESCRIPTION_LENGTH
        || description.length() > RecipeConstants.MAX_RECIPE_DESCRIPTION_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Recipe description cannot be null or blank and must be between %s and %s characters",
              RecipeConstants.MIN_RECIPE_DESCRIPTION_LENGTH,
              RecipeConstants.MAX_RECIPE_DESCRIPTION_LENGTH));
    }
    if (steps == null || steps.isBlank() || steps.length() < RecipeConstants.MIN_RECIPE_STEPS_LENGTH
        || steps.length() > RecipeConstants.MAX_RECIPE_STEPS_LENGTH) {
      throw new IllegalArgumentException(
          String.format(
              "Recipe steps cannot be null or blank and must be between %s and %s characters",
              RecipeConstants.MIN_RECIPE_STEPS_LENGTH,
              RecipeConstants.MAX_RECIPE_STEPS_LENGTH));
    }
    if (peopleCount < RecipeConstants.MIN_RECIPE_PEOPLE_COUNT
        || peopleCount > RecipeConstants.MAX_RECIPE_PEOPLE_COUNT) {
      throw new IllegalArgumentException(
          String.format("People count must be between %s and %s",
              RecipeConstants.MIN_RECIPE_PEOPLE_COUNT,
              RecipeConstants.MAX_RECIPE_PEOPLE_COUNT));
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
   * @throws NullPointerException     if grocery is null
   * @throws IllegalArgumentException if amount is less than or equal to 0
   */
  public void addGrocery(Grocery grocery, float amount) {
    if (grocery == null) {
      throw new NullPointerException("Grocery cannot be null");
    }
    if (amount < RecipeConstants.MIN_RECIPE_AMOUNT) {
      throw new IllegalArgumentException(String.format("amount of %s can't be less than %.2f",
          grocery.getGroceryName(),
          RecipeConstants.MIN_RECIPE_AMOUNT));
    }
    if (amount > RecipeConstants.MAX_RECIPE_AMOUNT) {
      throw new IllegalArgumentException(String.format("amount of %s can't be more than %.2f",
          grocery.getGroceryName(),
          RecipeConstants.MAX_RECIPE_AMOUNT));
    }
    groceries.computeIfPresent(grocery.getGroceryName(), (key, recipeGrocery) -> {
      float newAmount = recipeGrocery.amount() + amount;
      return new RecipeGrocery(grocery, newAmount);
    });
    groceries.putIfAbsent(grocery.getGroceryName(), new RecipeGrocery(grocery, amount));
  }

  /**
   * Get groceries in a recipe.
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
