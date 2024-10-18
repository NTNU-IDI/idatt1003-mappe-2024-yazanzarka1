package org.ntnu.application.commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.GroceryManager;
import org.ntnu.food.StorageUnit;

/**
 * Add Grocery to storage unit.
 *
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
   * Execute command.
   *
   * @return Boolean Rerender menu-commands
   *
   */
  @Override
  public Boolean execute() {
    groceryManager.displayGroceries();
    try {
      int groceryIndex = Integer.parseInt(inputHandler.getInput("Enter Grocery Index: "));

      float groceryAmount = Float.parseFloat(inputHandler.getInput("Enter Grocery Amount: "));

      Date groceryBestBeforeDate = new SimpleDateFormat("dd.MM.yyyy").parse(
          inputHandler.getInput("Enter Best before date (dd.mm.yyyy): "));

      storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(groceryIndex),
          groceryAmount, groceryBestBeforeDate);

      displayManager.showColoredMessage("Grocery added successfully", Color.GREEN);

      return true;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
      return true;
    }
  }

  @Override
  public String getDescription() {
    return "Add Grocery To Storage Unit";
  }
}
