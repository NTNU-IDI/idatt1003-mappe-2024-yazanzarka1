package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.RecipeStorageManager;
import edu.ntnu.idi.idatt.food.StorageUnit;

public class CookRecipeCommand implements Command {

  RecipeManager recipeManager;
  DisplayManager displayManager = new DisplayManager();
  InputHandler inputHandler = new InputHandler();
  StorageUnit storageUnit;

  public CookRecipeCommand(RecipeManager recipeManager, StorageUnit storageUnit) {
    this.recipeManager = recipeManager;
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
    RecipeStorageManager recipeStorageManager = new RecipeStorageManager(recipe, storageUnit);
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
