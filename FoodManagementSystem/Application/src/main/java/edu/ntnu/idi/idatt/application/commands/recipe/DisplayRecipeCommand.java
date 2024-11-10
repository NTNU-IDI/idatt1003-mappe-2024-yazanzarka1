package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
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
  public DisplayRecipeCommand(RecipeManager recipeManager, StorageUnit storageUnit) {
    this.recipeManager = recipeManager;
    this.displayManager = new DisplayManager();
    this.inputHandler = new InputHandler();
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
    recipeManager.displayRecipes();
    displayManager.showSpace();
    int recipeIndex = Integer.parseInt(inputHandler.getInput("Enter the index of the recipe: "));
    displayManager.showSpace();
    Recipe recipe = recipeManager.getRecipes().get(recipeIndex);

    //Display the recipe table
    final RecipeStorageManager recipeStorageManager =
        new RecipeStorageManager(recipe, storageUnit);
    recipeStorageManager.displayRecipe();

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
