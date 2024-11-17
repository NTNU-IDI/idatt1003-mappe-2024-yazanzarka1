package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.RecipeManager;

/**
 * Remove a recipe from the recipeManager.
 */
public class RemoveRecipeCommand implements Command {

  private final DisplayManager displayManager;
  private final InputHandler inputHandler;
  private final RecipeManager recipeManager;

  /**
   * Initiate command with a given recipeManager.
   *
   * @param recipeManager recipeManager where recipes are stored.
   */
  public RemoveRecipeCommand(RecipeManager recipeManager, DisplayManager displayManager, InputHandler inputHandler) {
    this.recipeManager = recipeManager;
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
    try {
      displayManager.showSpace();
      recipeManager.displayRecipes();
      displayManager.showSpace();

      int recipeIndex = Integer.parseInt(inputHandler.getInput("Enter the index of the recipe: "));
      displayManager.showSpace();
      recipeManager.removeRecipe(recipeIndex);
      displayManager.showMessage("Recipe removed");
      displayManager.showSpace();

      return false;

    } catch (Exception e) {
      throw new UserInputException("Invalid input: " + e.getMessage());
    }
  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Remove a recipe";
  }
}
