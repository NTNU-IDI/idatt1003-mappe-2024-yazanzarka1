package edu.ntnu.idi.idatt.application.commands;

import org.fusesource.jansi.Ansi.Color;
import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.GroceryManager;

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