package edu.ntnu.idi.idatt.application.commands;

import org.fusesource.jansi.Ansi.Color;
import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeManager;

/**
 * Display a recipe.
 */
public class DisplayRecipeCommand implements Command {

  private final RecipeManager recipeManager;
  private final DisplayManager displayManager;
  private final InputHandler inputHandler;

  /**
   * Initiate command with given recipeManager.
   *
   * @param recipeManager recipe manager where recipes are stored
   */
  public DisplayRecipeCommand(RecipeManager recipeManager) {
    this.recipeManager = recipeManager;
    this.displayManager = new DisplayManager();
    this.inputHandler = new InputHandler();
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
    try {
      int recipeIndex = Integer.parseInt(inputHandler.getInput("Enter the index of the recipe: "));
      displayManager.showSpace();
      Recipe recipe = recipeManager.getRecipes().get(recipeIndex);
      recipe.displayRecipe();
      displayManager.showMessage(String.format("Total recipe price: %.2f NOK", recipe.getRecipePrice()));
      displayManager.showSpace();

      return false;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
      return true;
    }

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
