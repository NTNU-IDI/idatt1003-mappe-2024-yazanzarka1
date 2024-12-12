package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.TableRepresentable;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryForRecipeException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * RecipeStorageManager is responsible for comparing groceries in a recipe with groceries in a
 * storage unit. RecipeStorageManager can remove groceries from storage if a recipe is cooked
 *
 * @author yazanzarka
 * @see Recipe
 * @see StorageUnit
 * @since 0.0.1
 */
public class RecipeStorageManager implements TableRepresentable {

  private final Recipe recipe;
  private final StorageUnit storageUnit;

  /**
   * Initiate RecipeStorageManager.
   *
   * @param recipe      recipe in a recipe
   * @param storageUnit storage unit of a recipe
   */
  public RecipeStorageManager(Recipe recipe, StorageUnit storageUnit) {
    if (recipe == null || storageUnit == null) {
      throw new IllegalArgumentException("Recipe and storage unit cannot be null");
    }
    this.recipe = recipe;
    this.storageUnit = storageUnit;
  }

  /**
   * Get recipe in a recipe.
   *
   * @return Grocery in a recipe
   */
  public Recipe getRecipe() {
    return recipe;
  }

  /**
   * Get storage unit of a recipe.
   *
   * @return StorageUnit of a recipe
   */
  public StorageUnit getStorageUnit() {
    return storageUnit;
  }


  /**
   * Serialize RecipeStorageManager to a tableData object with headers and rows.
   *
   * @return TableData with headers and rows
   * @see TableData
   */
  public TableData toTableData() {
    // Headers for the table
    List<String> headers =
        List.of("Grocery", "Unit", "Price", "Recipe Amount", "In Storage", "Storage Diff");

    // List of groceries in recipe
    List<List<String>> recipesList = new ArrayList<>();

    // Get all groceries in recipe
    recipe.getGroceries().values().forEach(recipeGrocery -> {
      // Get difference between amount of grocery in recipe and storage
      float storageDifference = getStorageDifference(recipeGrocery);
      float storageAmount = 0;

      // Get amount of grocery in storage
      StorageEntry storageEntry =
          storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
      if (storageEntry != null) {
        storageAmount = storageEntry.getQuantity();
      }
      // Add grocery to list
      recipesList.add(List.of(recipeGrocery.grocery().getGroceryName(),
          recipeGrocery.grocery().getUnit().getUnitName(),
          String.valueOf(recipeGrocery.grocery().getPricePerUnit()),
          String.valueOf(recipeGrocery.amount()), String.valueOf(storageAmount),
          formatDifference(storageDifference)));
    });

    return new TableData(headers, recipesList);

  }

  /**
   * Returns difference between amount of grocery in recipe and storage.
   *
   * @param recipeGrocery {@link RecipeGrocery} recipe grocery
   * @return {@link Float} storage difference
   */
  public float getStorageDifference(RecipeGrocery recipeGrocery) {
    StorageEntry grocery = storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
    if (grocery == null) {
      return -recipeGrocery.amount();
    }
    return grocery.getQuantity() - recipeGrocery.amount();
  }

  /**
   * Cook a recipe. Removes groceries from storage if they are available.
   *
   * @return {@link List} of groceries used to cook the recipe
   * @throws MissingGroceryForRecipeException if there are not enough groceries in storage.
   */
  public List<RecipeGrocery> cookRecipe() {
    List<RecipeGrocery> listOfNeededGroceries = new ArrayList<>();
    // get all groceries in recipe
    Collection<RecipeGrocery> groceriesInRecipe = recipe.getGroceries().values();

    // check if we have all the needed groceries in storage
    groceriesInRecipe.forEach(recipeGrocery -> {
      StorageEntry storageEntry =
          storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
      if (storageEntry == null || storageEntry.getQuantity() < recipeGrocery.amount()) {
        listOfNeededGroceries.add(recipeGrocery);
      }
    });

    // if we do not have all the needed groceries, we throw an exception
    if (!listOfNeededGroceries.isEmpty()) {
      throw new MissingGroceryForRecipeException(
          "You do not have enough groceries to cook this recipe.", listOfNeededGroceries);
    }

    // if we have all the needed groceries, we can remove them from storage to cook the recipe
    groceriesInRecipe.forEach(recipeGrocery -> {
      StorageEntry storageEntry =
          storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
      storageUnit.removeGrocery(storageEntry, recipeGrocery.amount());
    });

    return new ArrayList<>(groceriesInRecipe);
  }


  /**
   * return a string with color based on difference between recipe and storage. If difference is
   * negative, the string will be red. If difference is positive, the string will be green.
   *
   * @param difference difference between recipe and storage
   * @return {@link String} formatted string with color
   */
  String formatDifference(float difference) {
    if (difference < 0) {
      return Ansi.ansi().fg(Color.RED).a(difference).reset().toString();
    }
    return Ansi.ansi().fg(Color.GREEN).a(" " + difference).reset().toString();
  }

}
