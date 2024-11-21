package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.RecipeStorageManager;
import edu.ntnu.idi.idatt.food.StorageUnit;

/**
 * Display a recipe.
 */
public class DisplayRecipeCommand implements Command {

  private final RecipeManager recipeManager;
  private final DisplayManager displayManager;
  private final InputHandler inputHandler;
  private final StorageUnit storageUnit;

  /**
   * Initiate command with given recipeManager.
   *
   * @param recipeManager recipe manager where recipes are stored
   */
  public DisplayRecipeCommand(RecipeManager recipeManager, StorageUnit storageUnit,
      DisplayManager displayManager, InputHandler inputHandler) {
    this.recipeManager = recipeManager;
    this.displayManager = displayManager;
    this.inputHandler = inputHandler;
    this.storageUnit = storageUnit;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    displayManager.showSpace();
    TableData tableData = recipeManager.toTableData();
    displayManager.printTable(tableData);
    displayManager.showSpace();
    int recipeIndex = Integer.parseInt(inputHandler.getInput("Enter the index of the recipe: "));
    displayManager.showSpace();
    Recipe recipe;
    try {
      recipe = recipeManager.getRecipes().get(recipeIndex);
    } catch (IndexOutOfBoundsException e) {
      displayManager.showMessage("No recipe found at index " + recipeIndex);
      return false;
    }
    //Display the recipe table
    final RecipeStorageManager recipeStorageManager =
        new RecipeStorageManager(recipe, storageUnit);
    TableData recipeInStorageTableData = recipeStorageManager.toTableData();
    displayManager.printTable(recipeInStorageTableData);


    displayManager.showMessage(
        String.format("Total recipe price: %.2f NOK", recipe.getRecipePrice()));
    displayManager.showSpace();

    return false;


  }

  /**
   * Get command's description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Display Ingredients of a recipe";
  }
}
