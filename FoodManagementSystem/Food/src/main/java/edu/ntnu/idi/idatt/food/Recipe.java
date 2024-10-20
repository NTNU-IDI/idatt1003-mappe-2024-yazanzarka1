package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a recipe in a recipe manager.
 */
public class Recipe {

  private final String name;
  private final String description;
  private final List<RecipeGrocery> groceries;
  private final DisplayManager displayManager;

  /**
   * Initiate a recipe.
   *
   * @param name        name of a recipe
   * @param description description of a recipe
   * @param groceries   groceries in a recipe
   */
  public Recipe(String name, String description, List<RecipeGrocery> groceries) {
    this.name = name;
    this.groceries = groceries;
    this.description = description;
    displayManager = new DisplayManager();
  }

  /**
   * Initiate a recipe.
   *
   * @param name        name of a recipe
   * @param description description of a recipe
   */
  public Recipe(String name, String description) {
    this.name = name;
    this.groceries = new ArrayList<>();
    this.description = description;
    displayManager = new DisplayManager();
  }

  /**
   * Add a grocery to recipe.
   *
   * @param grocery Grocery to be added
   * @param amount  amount of grocery in a recipe
   */
  public void addGrocery(Grocery grocery, float amount) {
    RecipeGrocery recipeGrocery = new RecipeGrocery(grocery, amount);
    groceries.add(recipeGrocery);
  }

  /**
   * Get Groceries in a recipe.
   *
   * @return List of Groceries and value in a recipe
   */
  public List<RecipeGrocery> getGroceries() {
    return groceries;
  }


  /**
   * Display groceries in a recipe.
   */
  public void displayRecipe() {
    List<String> headers = List.of("Grocery", "Unit", "Amount");

    List<List<String>> groceriesList = groceries.stream()
        .map(entry -> List.of(entry.grocery().getGroceryName(),
            entry.grocery().getUnit().getUnitName(), String.valueOf(entry.amount())
        )).toList();

    displayManager.printTable(headers, groceriesList);
  }

  /**
   * Get the price of a recipe.
   *
   * @return float price of a recipe
   */
  public float getRecipePrice() {
    float price = 0;
    for (RecipeGrocery entry : groceries) {
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

}
