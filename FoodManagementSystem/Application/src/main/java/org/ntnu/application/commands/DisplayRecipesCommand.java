package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.food.RecipeManager;

/**
 * Display all recipes stored in recipeManager.
 */
public class DisplayRecipesCommand implements Command {

  RecipeManager recipeManager;
  DisplayManager displayManager;

  /**
   * Initiate command with given recipeManager.
   *
   * @param recipeManager RecipeManager where recipes are stored.
   */
  public DisplayRecipesCommand(RecipeManager recipeManager) {
    this.recipeManager = recipeManager;
    displayManager = new DisplayManager();
  }

  /**
   * .
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    displayManager.showSpace();
    recipeManager.displayRecipes();
    displayManager.showSpace();

    return false;
  }

  /**
   * .
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Display all recipes";
  }
}
