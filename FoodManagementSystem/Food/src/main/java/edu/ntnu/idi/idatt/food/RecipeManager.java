package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage recipes in a recipe manager.
 * RecipeManager can add, remove and display recipes.
 * RecipeManager is used by the application to manage recipes.
 * RecipeManager can also return recipes.
 */
public class RecipeManager {

  String name;
  List<Recipe> recipes;
  DisplayManager displayManager;

  /**
   * Initiate a recipe manager.
   */
  public RecipeManager(String name, DisplayManager displayManager) {
    this.name = name;
    recipes = new ArrayList<>();
    this.displayManager = displayManager;
  }

  /**
   * Add a recipe to recipe manager.
   *
   * @param recipe Recipe to be added
   */
  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  /**
   * Remove a recipe from recipe manager.
   *
   * @param index Index of recipe to be removed
   */
  public void removeRecipe(int index) {
    if (index < 0 || index >= recipes.size()) {
      throw new IndexOutOfBoundsException(
          "Index out of bounds: No recipe found at index " + index);
    }
    recipes.remove(index);
  }

  /**
   * Get recipes in a recipe manager.
   *
   * @return List of recipes in a recipe manager
   */
  public List<Recipe> getRecipes() {
    return recipes;
  }

  /**
   * Display recipes in a recipe manager. Display index, name and description of recipes. Display in
   * a table format.
   */
  public void displayRecipes() {

    List<String> headers = List.of("Index", "Name", "Description", "Price",  "People Count");
    List<List<String>> recipesList = new ArrayList<>();
    for (int i = 0; i < recipes.size(); i++) {
      recipesList.add(List.of(
          String.valueOf(i),
          recipes.get(i).getName(),
          recipes.get(i).getDescription(),
          String.format("%.2f", recipes.get(i).getRecipePrice()),
          String.valueOf(recipes.get(i).getPeopleCount())
      ));
    }

    displayManager.printTable(name, headers, recipesList);
  }

}
