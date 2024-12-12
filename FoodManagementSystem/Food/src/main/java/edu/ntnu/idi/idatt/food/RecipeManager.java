package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.TableRepresentable;
import edu.ntnu.idi.idatt.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * RecipeManager is responsible for managing recipes. RecipeManager can add and remove recipes.
 *
 * @author yazanzarka
 * @see Recipe
 * @since 0.0.1
 */
public class RecipeManager implements TableRepresentable {

  String name;
  List<Recipe> recipes;

  /**
   * Initiate a recipe manager.
   *
   * @param name Name of the recipe manager in the application - Example: "My Recipes" or "Cook
   *             Book"
   * @throws IllegalArgumentException if name is null or empty
   * @see Recipe
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
   * @param recipe {@link Recipe} Recipe to be added
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
   * @param index {@link Integer} Index of recipe to be removed
   * @throws IndexOutOfBoundsException if index is out of bounds
   */
  public void removeRecipe(int index) {
    if (index < 0 || index >= recipes.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds: No recipe found at index " + index);
    }
    recipes.remove(index);
  }

  /**
   * Get recipes in a recipe manager.
   *
   * @return {@link List} List of recipes in a recipe manager
   */
  public List<Recipe> getRecipes() {
    return recipes;
  }

  /**
   * Get the name of the recipe manager.
   *
   * @return {@link String} Name of the recipe manager
   */
  public String getName() {
    return name;
  }

  /**
   * Serialize RecipeManager to a tableData object with headers and rows.
   *
   * @return {@link TableData} TableData with headers and rows
   * @see TableData
   */
  public TableData toTableData() {
    // Headers for the table
    List<String> headers = List.of("ID", "Name", "Description", "Price", "People");
    List<List<String>> recipesList = IntStream.range(0, recipes.size()).mapToObj(
        i -> List.of(String.valueOf(i), Utils.truncateSentence(recipes.get(i).getName(), 4),
            Utils.truncateSentence(recipes.get(i).getDescription(), 4),
            String.format("%.2f", recipes.get(i).getRecipePrice()),
            String.valueOf(recipes.get(i).getPeopleCount()))).toList();

    return new TableData(headers, recipesList);

  }

}
