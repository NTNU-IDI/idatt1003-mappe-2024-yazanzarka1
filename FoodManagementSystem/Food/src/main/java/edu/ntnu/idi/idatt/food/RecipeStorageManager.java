package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;

/**
 * RecipeStorageManager is responsible for displaying a recipe, its groceries and Storage entries of
 * groceries.
 */
public class RecipeStorageManager {

  Recipe recipe;
  StorageUnit storageUnit;
  DisplayManager displayManager = new DisplayManager();

  /**l
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

  }


}
