package edu.ntnu.idi.idatt.application.commands;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.GroceryManager;

public class RemoveGroceryCommand implements Command {

  GroceryManager groceryManager;
  InputHandler inputHandler = new InputHandler();
  DisplayManager displayManager = new DisplayManager();

  /**
   * Remove Grocery from GroceryManager.
   *
   * @param groceryManager GroceryManager to execute needed actions related to GroceryManager
   */
  public RemoveGroceryCommand(GroceryManager groceryManager) {
    this.groceryManager = groceryManager;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    groceryManager.displayGroceries();
    int groceryIndex = Integer.parseInt(inputHandler.getInput("Enter Grocery Index: "));
    groceryManager.removeGrocery(groceryIndex);
    displayManager.showFancyMessage("Grocery removed successfully");
    return true;
  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Remove Grocery from application";
  }
}
