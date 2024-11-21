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
 * Command to cook a recipe.
 */
public class CookRecipeCommand implements Command {

  RecipeManager recipeManager;
  DisplayManager displayManager;
  InputHandler inputHandler;
  StorageUnit storageUnit;

  /**
   * Initiate the command with a RecipeManager.
   *
   * @param recipeManager RecipeManager to add recipe to.
   * @param storageUnit StorageUnit to store groceries in
   * @param displayManager DisplayManager to display messages
   * @param inputHandler InputHandler to handle user input
   */
  public CookRecipeCommand(RecipeManager recipeManager, StorageUnit storageUnit, DisplayManager displayManager, InputHandler inputHandler) {
    this.recipeManager = recipeManager;
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;
    this.inputHandler = inputHandler;
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
    Recipe recipe = recipeManager.getRecipes().get(recipeIndex);
    RecipeStorageManager recipeStorageManager = new RecipeStorageManager(recipe, storageUnit, displayManager);
    recipeStorageManager.cookRecipe();
    displayManager.showSpace();

    return false;

  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Cook a recipe";
  }
}
