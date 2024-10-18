package org.ntnu.application.commands;

import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.GroceryManager;

/**
 * Remove grocery from grocery manager.
 */
public class RemoveGroceryFromGroceryManagerCommand implements Command {

  GroceryManager groceryManager;
  InputHandler inputHandler = new InputHandler();
  DisplayManager displayManager = new DisplayManager();
  /**
   * Initiate command.
   */
  public RemoveGroceryFromGroceryManagerCommand(GroceryManager groceryManager) {
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
      int groceryIndex = Integer.parseInt(inputHandler.getInput("Enter the index of the grocery: "));
      groceryManager.removeGrocery(groceryManager.getAvailableGroceries().get(groceryIndex));
      displayManager.showColoredMessage("Grocery removed successfully", Color.GREEN);
      return false;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
      return true;
    }
  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Remove grocery from grocery manager";
  }
}
