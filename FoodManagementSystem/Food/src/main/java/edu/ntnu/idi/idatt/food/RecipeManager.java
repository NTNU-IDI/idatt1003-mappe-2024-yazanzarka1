package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.TableRepresentable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * RecipeManager is responsible for managing recipes. RecipeManager can add and remove recipes.
 */
public class RecipeManager implements TableRepresentable {

  String name;
  List<Recipe> recipes;
  DisplayManager displayManager;

  /**
   * Initiate a recipe manager.
   *
   * @param name Name of the recipe manager in the application - Example: "My Recipes" or "Cook Book"
   */
  public RecipeManager(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.name = name;
    recipes = new ArrayList<>();
  }

  /**
   * Add a recipe to recipe manager.
   *
   * @param recipe Recipe to be added
   */
  public void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe cannot be null");
    }
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
   * Get the name of the recipe manager.
   *
   * @return Name of the recipe manager
   */
  public String getName() {
    return name;
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
