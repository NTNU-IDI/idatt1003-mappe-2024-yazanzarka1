package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent a recipe in a recipe manager.
 */
public class Recipe {

  private final String name;
  private final String description;
  private final Map<String, RecipeGrocery> groceries;
  private final DisplayManager displayManager;
  private int peopleCount;
  private final String steps;


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
    this.peopleCount = 4;
    this.steps = "";
    displayManager = new DisplayManager();
  }

  /**
   * Initiate a recipe.
   *
   * @param name        name of a recipe
   * @param description description of a recipe
   * @param steps       steps to make a recipe
   * @param peopleCount number of people the recipe is for
   */
  public Recipe(String name, String description, String steps, int peopleCount) {
    this.name = name;
    this.groceries = new HashMap<>();
    this.description = description;
    displayManager = new DisplayManager();
    this.steps = steps;
    this.peopleCount = peopleCount;
  }

  /**
   * Add a grocery to recipe.
   *
   * @param grocery Grocery to be added
   * @param amount  amount of grocery in a recipe
   */
  public void addGrocery(Grocery grocery, float amount) {
    groceries.putIfAbsent(grocery.getGroceryName(), new RecipeGrocery(grocery, amount));
    groceries.computeIfPresent(grocery.getGroceryName(), (k, v) -> {
      float newAmount = v.amount() + amount;
      groceries.put(k, new RecipeGrocery(grocery, newAmount));
      return v;
    });


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
   * Display groceries in a recipe.
   */
  public void displayRecipe() {
    List<String> headers = List.of("Grocery", "Unit", "Amount");

    List<List<String>> groceriesList = new ArrayList<>();
    for (RecipeGrocery entry : groceries.values()) {
      groceriesList.add(List.of(entry.grocery().getGroceryName(),
          entry.grocery().getUnit().getClass().getSimpleName(), String.valueOf(entry.amount())));
    }

    displayManager.printTable(headers, groceriesList);
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
