package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Manage recipes in a recipe manager. RecipeManager can add, remove and display recipes.
 * RecipeManager is used by the application to manage recipes. RecipeManager can also return
 * recipes.
 */
public class RecipeManager {

  String name;
  List<Recipe> recipes;
  DisplayManager displayManager;

  /**
   * Initiate a recipe manager.
   */
  public RecipeManager(String name) {
    this.name = name;
    recipes = new ArrayList<>();
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
   * Serialize RecipeManager to a tableData object with headers and rows.
   *
   * @return TableData with headers and rows
   * @see TableData
   */
  public TableData toTableData() {
    // Headers for the table
    List<String> headers = List.of("Index", "Name", "Description", "Price", "People Count");
    List<List<String>> recipesList = IntStream.range(0, recipes.size())
        .mapToObj(i -> List.of(
            String.valueOf(i),
            recipes.get(i).getName(),
            recipes.get(i).getDescription(),
            String.format("%.2f", recipes.get(i).getRecipePrice()),
            String.valueOf(recipes.get(i).getPeopleCount())
        ))
        .toList();

    return new TableData(headers, recipesList);

  }

}
