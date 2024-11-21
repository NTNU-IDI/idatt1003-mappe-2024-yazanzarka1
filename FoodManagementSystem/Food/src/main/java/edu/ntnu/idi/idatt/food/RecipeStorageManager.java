package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryInStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * RecipeStorageManager is responsible for displaying and managing a recipe, its groceries and
 * Storage entries of groceries.
 * RecipeStorageManager can display groceries in a recipe and their storage entries.
 * RecipeStorageManager can also cook a recipe.
 * RecipeStorageManager is used by the application to manage recipes.
 */
public class RecipeStorageManager {

  private final Recipe recipe;
  private final StorageUnit storageUnit;

  /**
   * Initiate RecipeStorageManager.
   *
   * @param recipe      recipe in a recipe
   * @param storageUnit storage unit of a recipe
   */
  public RecipeStorageManager(Recipe recipe, StorageUnit storageUnit) {
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
    List<String> headers = List.of("Grocery", "Unit", "Price", "Recipe Amount", "In Storage", "Storage Diff");
    List<List<String>> recipesList = new ArrayList<>();
    recipe.getGroceries().values().forEach(recipeGrocery -> {
      float storageDifference = getStorageDifference(recipeGrocery);
      float storageAmount = 0;
      StorageEntry storageEntry =
          storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
      if (storageEntry != null) {
        storageAmount = storageEntry.getQuantity();
      }
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
   * @param recipeGrocery recipe grocery
   * @return storage difference
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
   * @throws MissingGroceryInStorage if there are not enough groceries in storage
   */
  public void cookRecipe() {
    List<RecipeGrocery> listOfNeededGroceries = new ArrayList<>();
    for (RecipeGrocery recipeGrocery : recipe.getGroceries().values()) {
      StorageEntry storageEntry =
          storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
      if (storageEntry == null || storageEntry.getQuantity() < recipeGrocery.amount()) {
        listOfNeededGroceries.add(recipeGrocery);
        continue;
      }
    }
    if (!listOfNeededGroceries.isEmpty()) {
      throw new MissingGroceryInStorage(
          "You do not have enough groceries to cook this recipe.");
    }
    for (RecipeGrocery recipeGrocery : recipe.getGroceries().values()) {
      StorageEntry storageEntry =
          storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
      storageUnit.removeGrocery(storageEntry, recipeGrocery.amount());
    }
  }


  private String formatDifference(float difference) {
    if (difference < 0) {
      return Ansi.ansi().fg(Color.RED).a(difference).reset().toString();
    }
    return Ansi.ansi().fg(Color.GREEN).a(" " + difference).reset().toString();
  }

}
