package org.ntnu.food;

import java.util.ArrayList;
import java.util.List;
import org.ntnu.console.DisplayManager;

/**
 * Manage recipes in a recipe manager.
 */
public class RecipeManager {

  List<Recipe> recipes;
  DisplayManager displayManager;

  /**
   * Initiate a recipe manager.
   */
  public RecipeManager() {
    recipes = new ArrayList<>();
    displayManager = new DisplayManager();
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
   * @param recipe Recipe to be removed
   */
  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
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
   * Display recipes in a recipe manager.
   * Display index, name and description of recipes.
   * Display in a table format.
   *
   */
  public void displayRecipes() {

    List<String> headers = List.of("Index", "Name", "Description");
    List<List<String>> recipesList = new ArrayList<>();
    for (int i = 0; i < recipes.size(); i++) {
      recipesList.add(List.of(
          String.valueOf(i),
          recipes.get(i).name,
          recipes.get(i).description
      ));
    }
    displayManager.printTable(headers, recipesList);
  }

}
