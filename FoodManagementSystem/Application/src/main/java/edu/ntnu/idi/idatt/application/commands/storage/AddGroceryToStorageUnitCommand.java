package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.fusesource.jansi.Ansi.Color;

/**
 * Add Grocery to storage unit.
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
   */
  public AddGroceryToStorageUnitCommand(GroceryManager groceryManager, StorageUnit storageUnit) {
    this.groceryManager = groceryManager;
    this.storageUnit = storageUnit;
    this.inputHandler = new InputHandler();
    this.displayManager = new DisplayManager();
  }

  /**
   * Add Grocery Command.
   *
   * @param groceryManager groceryManage to get available groceries in app.
   * @param storageUnit    storageUnit where grocery will be added
   * @param inputHandler   InputHandler to get input from user
   */
  public AddGroceryToStorageUnitCommand(GroceryManager groceryManager, StorageUnit storageUnit,
      InputHandler inputHandler) {
    this.groceryManager = groceryManager;
    this.storageUnit = storageUnit;
    this.inputHandler = inputHandler;
    this.displayManager = new DisplayManager();
  }

  /**
   * Execute command.
   *
   * @return Boolean Rerender menu-commands
   */
  @Override
  public Boolean execute() {
    try {
      int groceryIndex = Integer.parseInt(inputHandler.getInput("Enter Grocery Index: "));

      float groceryAmount = Float.parseFloat(inputHandler.getInput("Enter Grocery Amount: "));
      Date groceryBestBeforeDate;
      try {
        groceryBestBeforeDate = new SimpleDateFormat("dd.MM.yyyy").parse(
            inputHandler.getInput("Enter Best before date (dd.mm.yyyy): "));
      } catch (ParseException e) {
        throw new UserInputException("Invalid date format - " + e.getMessage());
      }

      storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(groceryIndex),
          groceryAmount, groceryBestBeforeDate);

      displayManager.showColoredMessage("Grocery added successfully", Color.GREEN);

      return true;
    } catch (Exception e) {
      throw new UserInputException("Invalid input: " + e.getMessage());
    }

  }

  @Override
  public String getDescription() {
    return "Add Grocery To Storage Unit";
  }
}
