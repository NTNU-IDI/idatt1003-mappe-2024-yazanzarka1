package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.console.validators.IntegerValidator;
import edu.ntnu.idi.idatt.food.RecipeManager;
import org.fusesource.jansi.Ansi;

/**
 * Remove a recipe from the recipeManager.
 *
 * @see Command
 * @see RecipeManager
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
  public RemoveRecipeCommand(RecipeManager recipeManager, DisplayManager displayManager,
      InputHandler inputHandler) {
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
      TableData tableData = recipeManager.toTableData();
      displayManager.printTable(tableData);
      displayManager.showSpace();

      displayManager.showColoredMessage(
          String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
          Ansi.Color.YELLOW);

      // Get recipe index from user
      int recipeIndex = inputHandler.getInt("Enter the ID of the recipe: ",
          new IntegerValidator("Invalid ID", 0, recipeManager.getRecipes().size() - 1));

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
