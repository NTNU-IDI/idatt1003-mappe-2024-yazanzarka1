package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent a recipe in a recipe manager.
 */
public class Recipe {

  String name;
  String description;
  Map<Grocery, Float> groceries;
  DisplayManager displayManager;

  /**
   * Initiate a recipe.
   *
   * @param name        name of a recipe
   * @param description description of a recipe
   * @param groceries   groceries in a recipe
   */
  public Recipe(String name, String description, Map<Grocery, Float> groceries) {
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
    this.groceries = new HashMap<>();
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
    groceries.computeIfPresent(grocery, (key, value) ->
        groceries.put(key, groceries.get(grocery) + amount)
    );
    groceries.put(grocery, amount);
  }

  /**
   * Get Groceries in a recipe.
   *
   * @return Map of Groceries and value in a recipe
   */
  public Map<Grocery, Float> getGroceries() {
    return groceries;
  }


  /**
   * Display groceries in a recipe.
   */
  public void displayRecipe() {
    List<String> headers = List.of("Grocery", "Unit", "Amount");
    List<List<String>> groceriesList = groceries.keySet().stream()
        .map(grocery -> List.of(grocery.getGroceryName(), grocery.getUnit().getUnitName(),
            groceries.get(grocery).toString())).toList();
    displayManager.printTable(headers, groceriesList);
  }

  /**
   * Get the price of a recipe.
   *
   * @return float price of a recipe
   */
  public float getRecipePrice() {
    float price = 0;
    for (Grocery grocery : groceries.keySet()) {
      price += groceries.get(grocery) * grocery.getPricePerUnit();
    }
    return price;
  }

}
