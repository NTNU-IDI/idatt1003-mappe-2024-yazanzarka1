package org.ntnu.application.commands;

import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.GroceryManager;

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
    try {
      int groceryIndex = Integer.parseInt(inputHandler.getInput("Enter Grocery Index: "));
      groceryManager.removeGrocery(groceryManager.getAvailableGroceries().get(groceryIndex));
      displayManager.showFancyMessage("Grocery removed successfully");
      return true;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
    }
    return false;
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
