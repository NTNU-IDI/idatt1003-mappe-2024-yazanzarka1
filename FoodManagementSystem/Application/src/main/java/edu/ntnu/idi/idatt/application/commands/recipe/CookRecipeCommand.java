package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.IntegerValidator;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeGrocery;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.RecipeStorageManager;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryForRecipeException;
import java.util.List;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Command to cook a recipe.
 *
 * @see Command
 * @see Recipe
 * @see RecipeManager
 * @see RecipeStorageManager
 */
public class CookRecipeCommand implements Command {

  RecipeManager recipeManager;
  DisplayManager displayManager;
  InputHandler inputHandler;
  StorageUnit storageUnit;

  /**
   * Initiate the command with a RecipeManager.
   *
   * @param recipeManager  RecipeManager to add recipe to.
   * @param storageUnit    StorageUnit to store groceries in
   * @param displayManager DisplayManager to display messages
   * @param inputHandler   InputHandler to handle user input
   */
  public CookRecipeCommand(RecipeManager recipeManager, StorageUnit storageUnit,
      DisplayManager displayManager, InputHandler inputHandler) {
    this.recipeManager = recipeManager;
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;
    this.inputHandler = inputHandler;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   * @throws MissingGroceryForRecipeException if groceries is missing from the storage
   */
  @Override
  public Boolean execute() {
    // Display available recipes
    displayManager.showSpace();
    TableData tableData = recipeManager.toTableData();
    displayManager.printTable(tableData);
    displayManager.showSpace();

    displayManager.showColoredMessage(
        String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
        Ansi.Color.YELLOW);

    // Get recipe index from user
    int recipeIndex = inputHandler.getInt("Enter the ID of the recipe: ",
        new IntegerValidator("Invalid index", 0, recipeManager.getRecipes().size() - 1));
    displayManager.showSpace();

    // Get recipe from recipeManager
    Recipe recipe = recipeManager.getRecipes().get(recipeIndex);

    // create RecipeStorageManager Instance to compare recipe with storage
    RecipeStorageManager recipeStorageManager = new RecipeStorageManager(recipe, storageUnit);
    List<RecipeGrocery> recipeGroceryList;

    try {

      // cook recipe
      recipeGroceryList = recipeStorageManager.cookRecipe();

      // display used groceries in green
      recipeGroceryList.forEach(recipeGrocery -> displayManager.showColoredMessage(
          String.format("Used %.2f %s %s", recipeGrocery.amount(),
              recipeGrocery.grocery().getUnit().getUnitName(),
              recipeGrocery.grocery().getGroceryName()), Color.GREEN));
      // display success message
      displayManager.showColoredMessage("Recipe cooked successfully!", Color.GREEN);
    } catch (MissingGroceryForRecipeException e) {
      // display missing groceries in red
      displayManager.showMessage(e.getMessage());
      e.getGroceries().forEach(recipeGrocery -> displayManager.showColoredMessage(
          String.format("Missing grocery: %.2f %s %s", recipeGrocery.amount(),
              recipeGrocery.grocery().getUnit().getUnitName(),
              recipeGrocery.grocery().getGroceryName()), Color.RED));
    }
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
