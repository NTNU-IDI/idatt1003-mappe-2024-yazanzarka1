package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.console.validators.DateValidator;
import edu.ntnu.idi.idatt.console.validators.FloatValidator;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.food.constants.StorageEntryConstants;
import java.util.Date;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Add Grocery to storage unit.
 *
 * @see Command
 * @see StorageUnit
 */
public class AddGroceryToStorageUnitCommand implements Command {

  final GroceryManager groceryManager;
  final StorageUnit storageUnit;
  final InputHandler inputHandler;
  final DisplayManager displayManager;

  /**
   * Add Grocery Command.
   *
   * @param groceryManager groceryManage to get available groceries in app.
   * @param storageUnit    storageUnit where grocery will be added
   * @param inputHandler   InputHandler to get input from user
   */
  public AddGroceryToStorageUnitCommand(GroceryManager groceryManager, StorageUnit storageUnit,
      DisplayManager displayManager, InputHandler inputHandler) {
    this.groceryManager = groceryManager;
    this.storageUnit = storageUnit;
    this.inputHandler = inputHandler;
    this.displayManager = displayManager;
  }

  /**
   * Execute command.
   *
   * @return Boolean Rerender menu-commands
   */
  @Override
  public Boolean execute() {
    try {
      // Display available groceries
      TableData tableData = groceryManager.toTableData();
      displayManager.printTable(tableData);

      displayManager.showColoredMessage(
          String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
          Ansi.Color.YELLOW);

      // Get grocery name from user
      String groceryName = inputHandler.getString("Enter Grocery Name: ", new StringValidator(
          String.format("Grocery name should be between %s and %s characters",
              GroceryConstants.MIN_GROCERY_NAME_LENGTH, GroceryConstants.MAX_GROCERY_NAME_LENGTH),
          GroceryConstants.MIN_GROCERY_NAME_LENGTH, GroceryConstants.MAX_GROCERY_NAME_LENGTH));

      // Get grocery from groceryManager
      var groceryToBeAdded = groceryManager.getAvailableGroceries().stream()
          .filter(grocery -> grocery.getGroceryName().equalsIgnoreCase(groceryName)).findFirst()
          .orElseThrow(() -> new UserInputException("Grocery not found"));

      // Get grocery amount from user
      float groceryAmount = inputHandler.getFloat(
          String.format("Enter amount of %s (%s-%s): ", groceryToBeAdded.getGroceryName(),
              StorageEntryConstants.MIN_QUANTITY, StorageEntryConstants.MAX_QUANTITY),
          new FloatValidator("Invalid amount", StorageEntryConstants.MIN_QUANTITY,
              StorageEntryConstants.MAX_QUANTITY));

      // Get grocery best before date from user
      Date groceryBestBeforeDate = inputHandler.getDate("Enter Best before date (dd.mm.yyyy): ",
          new DateValidator("Invalid date format"));

      // Add grocery to storage unit
      storageUnit.addGrocery(groceryToBeAdded, groceryAmount, groceryBestBeforeDate);

      displayManager.showColoredMessage("Grocery added successfully", Color.GREEN);
      return false;
    } catch (Exception e) {
      throw new UserInputException("Invalid input: " + e.getMessage());
    }

  }

  @Override
  public String getDescription() {
    return String.format("Add grocery to %s", storageUnit.getName().toLowerCase());
  }
}
