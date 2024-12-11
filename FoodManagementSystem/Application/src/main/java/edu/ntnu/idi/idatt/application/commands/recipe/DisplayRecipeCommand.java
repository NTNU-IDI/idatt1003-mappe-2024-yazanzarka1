package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.IntegerValidator;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.RecipeStorageManager;
import edu.ntnu.idi.idatt.food.StorageUnit;
import org.fusesource.jansi.Ansi.Color;

/**
 * Display a single recipe and its details.
 *
 * @see Command
 * @see Recipe
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
   * @param storageUnit storage unit where groceries are stored
   * @param displayManager display manager to display messages
   * @param inputHandler input handler to handle user input
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
    //Display the available recipes
    displayManager.showSpace();
    TableData tableData = recipeManager.toTableData();
    displayManager.printTable(tableData);
    displayManager.showSpace();

    displayManager.showColoredMessage(
        String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
        Color.YELLOW);

    //Get the index of the recipe from the user
    int recipeIndex = inputHandler.getInt("Enter the ID of the recipe: ",
        new IntegerValidator("Invalid ID", 0, recipeManager.getRecipes().size() - 1));
    displayManager.showSpace();

    //Get the recipe from the recipe manager
    Recipe recipe;
    try {
      recipe = recipeManager.getRecipes().get(recipeIndex);
    } catch (IndexOutOfBoundsException e) {
      displayManager.showMessage("No recipe found with given ID. " + recipeIndex);
      return false;
    }
    //Display the recipe table
    final RecipeStorageManager recipeStorageManager =
        new RecipeStorageManager(recipe, storageUnit);

    //Display the recipe table
    TableData recipeInStorageTableData = recipeStorageManager.toTableData();
    displayManager.printTable(recipe.getName(), recipeInStorageTableData);

    // Display the recipe description
    displayManager.showSpace();
    displayManager.showFancyMessage("Description of the recipe:");
    displayManager.showMessage(recipe.getDescription());


    // Display the steps of the recipe
    displayManager.showSpace();
    displayManager.showFancyMessage("Steps to make the recipe:");
    displayManager.showMessage(recipe.getSteps());

    //Display the total price of the recipe
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
