package edu.ntnu.idi.idatt.application.commands.grocery;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.GroceryManager;

/**
 * Display application's groceries.
 */
public class DisplayGroceriesCommand implements Command {

  final GroceryManager groceryManager;
  final DisplayManager displayManager;

  /**
   * DisplayGroceriesCommand constructor.
   *
   * @param groceryManager groceryManager we display groceries from
   * @param displayManager displayManager to display messages
   */
  public DisplayGroceriesCommand(GroceryManager groceryManager, DisplayManager displayManager) {
    this.groceryManager = groceryManager;
    this.displayManager = displayManager;


  }

  @Override
  public Boolean execute() {
    TableData tableData = groceryManager.toTableData();
    displayManager.printTable(tableData);
    return false;
  }

  @Override
  public String getDescription() {
    return "Display available groceries";
  }
}

