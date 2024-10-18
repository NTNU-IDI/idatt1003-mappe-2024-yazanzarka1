package edu.ntnu.idi.idatt.application.commands;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.food.GroceryManager;

/**
 * Display application's groceries.
 */
public class DisplayGroceriesCommand implements Command {

  final GroceryManager groceryManager;

  /**
   *.
   *
   * @param groceryManager groceryManager we display groceries from
   */
  public DisplayGroceriesCommand(GroceryManager groceryManager) {
    this.groceryManager = groceryManager;
  }

  @Override
  public Boolean execute() {
    groceryManager.displayGroceries();
    return false;
  }

  @Override
  public String getDescription() {
    return "Display Available Groceries";
  }
}

