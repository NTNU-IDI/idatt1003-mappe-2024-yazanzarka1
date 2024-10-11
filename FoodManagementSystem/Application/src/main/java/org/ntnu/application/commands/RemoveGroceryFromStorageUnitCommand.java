package org.ntnu.application.commands;

import java.util.List;
import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.StorageEntry;
import org.ntnu.food.StorageUnit;

/**
 * Remove grocery from storage unit.
 */
public class RemoveGroceryFromStorageUnitCommand implements Command {

  final InputHandler inputHandler;
  final StorageUnit storageUnit;
  final DisplayManager displayManager;

  /**
   * Initiate a remove grocery from storage unit command.
   *
   * @param storageUnit Storage unit to remove grocery from
   */
  public RemoveGroceryFromStorageUnitCommand(StorageUnit storageUnit) {
    inputHandler = new InputHandler();
    this.storageUnit = storageUnit;
    displayManager = new DisplayManager();
  }

  /**
   * Execute remove grocery from storage unit command.
   *
   * @return true if commands should be shown to user again, false if not
   */
  @Override
  public Boolean execute() {
    storageUnit.displayGroceries();
    try {
      String storageEntryName = inputHandler.getInput("Enter name of grocery to remove: ");

      List<StorageEntry> storageEntries = storageUnit.findGrocery(storageEntryName);
      if (storageEntries.isEmpty()) {
        displayManager.showColoredMessage("Error: Grocery not found", Color.RED);
        return true;
      }

      if (storageEntries.size() > 1) {
        displayManager.showColoredMessage("Error: Multiple groceries found", Color.RED);
        return true;
      }

      float quantity = Float.parseFloat(inputHandler.getInput("Enter quantity to remove: "));

      storageUnit.removeGrocery(storageEntries.getFirst(), quantity);
      return false;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
      return false;
    }
  }

  /**
   * Get description of remove grocery from storage unit command.
   *
   * @return Description of remove grocery from storage unit command
   */
  @Override
  public String getDescription() {
    return "Remove grocery from storage unit";
  }
}
