package edu.ntnu.idi.idatt.application.commands.grocery;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import org.fusesource.jansi.Ansi;
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
   * @param inputHandler   InputHandler to handle user input
   */
  public RemoveGroceryFromGroceryManagerCommand(GroceryManager groceryManager,
      DisplayManager displayManager,
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

    // Display available groceries
    TableData tableData = groceryManager.toTableData();
    displayManager.printTable(tableData);

    displayManager.showColoredMessage(
        String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
        Ansi.Color.YELLOW);

    // Get grocery name from user
    String groceryName = inputHandler.getString("Enter the name of the grocery: ",
        new StringValidator(String.format("Grocery name must be between %s and %s characters",
            GroceryConstants.MIN_GROCERY_NAME_LENGTH, GroceryConstants.MAX_GROCERY_NAME_LENGTH),
            GroceryConstants.MIN_GROCERY_NAME_LENGTH, GroceryConstants.MAX_GROCERY_NAME_LENGTH));

    // Check if grocery exists
    Grocery grocery = groceryManager.getAvailableGroceries().stream()
        .filter(g -> g.getGroceryName().equalsIgnoreCase(groceryName)).findFirst().orElse(null);

    // if grocery not found, throw exception
    if (grocery == null) {
      throw new GroceryNotFoundException("Grocery not found: " + groceryName);
    }
    // remove grocery from grocery manager and display message
    groceryManager.removeGrocery(grocery);
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
    return "Remove grocery from application";
  }
}
