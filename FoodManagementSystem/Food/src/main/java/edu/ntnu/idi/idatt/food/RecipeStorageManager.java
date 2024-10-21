package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryInStorage;
import java.util.ArrayList;
import java.util.List;
import org.fusesource.jansi.Ansi.Color;

/**
 * RecipeStorageManager is responsible for displaying and managing a recipe, its groceries and
 * Storage entries of groceries.
 */
public class RecipeStorageManager {

  private final Recipe recipe;
  private final StorageUnit storageUnit;
  DisplayManager displayManager = new DisplayManager();

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
   * Display groceries in a recipe and their storage entries. Display name, description, groceries,
   * steps of a recipe and storage entries.
   */
  public void displayRecipe() {
    List<String> header =
        List.of("Grocery", "Unit", "Price", "Recipe Amount", "In Storage", "Storage Diff");
    List<List<String>> rows = new ArrayList<>();
    recipe.getGroceries().forEach(recipeGrocery -> {
      float storageDifference = getStorageDifference(recipeGrocery);
      float storageAmount = 0;
      StorageEntry storageEntry =
          storageUnit.getGroceries().get(recipeGrocery.grocery().getGroceryName());
      if (storageEntry != null) {
        storageAmount = storageEntry.getQuantity();
      }
      rows.add(List.of(recipeGrocery.grocery().getGroceryName(),
          recipeGrocery.grocery().getUnit().getUnitName(),
          String.valueOf(recipeGrocery.grocery().getPricePerUnit()),
          String.valueOf(recipeGrocery.amount()), String.valueOf(storageAmount),
          String.valueOf(storageDifference)));
    });

    displayManager.printTable(header, rows);
  }


  /**
   * Returns difference between amount of grocery in recipe and storage.
   *
   * @param recipeGrocery recipe grocery
   * @return storage difference
   */
  public float getStorageDifference(RecipeGrocery recipeGrocery) {
    StorageEntry grocery = storageUnit.getGroceries().get(recipeGrocery.grocery().getGroceryName());
    if (grocery == null) {
      return -recipeGrocery.amount();
    }
    return grocery.getQuantity() - recipeGrocery.amount();
  }

  public void cookRecipe() {
    List<RecipeGrocery> listOfNeededGroceries = new ArrayList<>();
    for (RecipeGrocery recipeGrocery : recipe.getGroceries()) {
      StorageEntry storageEntry =
          storageUnit.getGroceries().get(recipeGrocery.grocery().getGroceryName());
      listOfNeededGroceries = new ArrayList<>();
      if (storageEntry == null) {
        listOfNeededGroceries.add(recipeGrocery);
        continue;
      }
      if (storageEntry.getQuantity() < recipeGrocery.amount()) {
        listOfNeededGroceries.add(recipeGrocery);
      }
    }
    if (!listOfNeededGroceries.isEmpty()) {

      displayRecipe();
      throw new MissingGroceryInStorage(
          "You do not have enough groceries to cook this recipe. Check the table above for more information.");
    }
    for (RecipeGrocery recipeGrocery : recipe.getGroceries()) {
      StorageEntry storageEntry =
          storageUnit.getGroceries().get(recipeGrocery.grocery().getGroceryName());
      storageUnit.removeGrocery(storageEntry, recipeGrocery.amount());
      displayManager.showColoredMessage(
          String.format("Removed %.2f %s from storage. You have %.2f %s left.",
              recipeGrocery.amount(), recipeGrocery.grocery().getGroceryName(),
              storageEntry.getQuantity(), recipeGrocery.grocery().getUnit().getUnitName()),
          Color.GREEN);
    }
    displayManager.showColoredMessage("Recipe cooked", Color.GREEN);
  }


}
