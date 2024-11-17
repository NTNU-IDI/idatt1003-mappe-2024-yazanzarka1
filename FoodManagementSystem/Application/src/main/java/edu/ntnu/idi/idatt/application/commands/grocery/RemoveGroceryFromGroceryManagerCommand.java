package edu.ntnu.idi.idatt.application.commands.grocery;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.GroceryManager;
import org.fusesource.jansi.Ansi.Color;

/**
 * Remove grocery from grocery manager.
 */
public class RemoveGroceryFromGroceryManagerCommand implements Command {

  GroceryManager groceryManager;
  InputHandler inputHandler;
  DisplayManager displayManager;

  /**
   * Initiate command.
   *
   * @param groceryManager GroceryManager to remove grocery from
   * @param displayManager DisplayManager to display messages
   * @param inputHandler InputHandler to handle user input
   */
  public RemoveGroceryFromGroceryManagerCommand(GroceryManager groceryManager, DisplayManager displayManager,
      InputHandler inputHandler) {
    this.groceryManager = groceryManager;
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
    groceryManager.displayGroceries();
    String groceryName = inputHandler.getInput("Enter the name of the grocery: ");
    groceryManager.removeGrocery(groceryName);
    displayManager.showColoredMessage("Grocery removed successfully", Color.GREEN);
    return false;
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
