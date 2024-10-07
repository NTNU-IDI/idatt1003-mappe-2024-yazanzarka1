package org.ntnu.food;

import java.util.ArrayList;
import java.util.List;
import org.ntnu.console.DisplayManager;

public class RecipeManager {

  List<Recipe> recipes;
  DisplayManager displayManager;

  public RecipeManager() {
    recipes = new ArrayList<>();
    displayManager = new DisplayManager();
  }

  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

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
