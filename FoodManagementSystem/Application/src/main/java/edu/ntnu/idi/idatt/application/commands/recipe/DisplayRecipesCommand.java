package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.RecipeManager;

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
  public DisplayRecipesCommand(RecipeManager recipeManager, DisplayManager displayManager) {
    this.recipeManager = recipeManager;
    this.displayManager = displayManager;
  }

  /**
   *  Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    displayManager.showSpace();
    TableData tableData = recipeManager.toTableData();
    displayManager.printTable(recipeManager.getName(), tableData);
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
