package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.util.List;
import org.fusesource.jansi.Ansi.Color;

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
   * @param displayManager Display manager to display messages
   * @param inputHandler Input handler to get input from user
   */
  public RemoveGroceryFromStorageUnitCommand(StorageUnit storageUnit, DisplayManager displayManager, InputHandler inputHandler) {
    this.inputHandler = inputHandler;
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;
  }

  /**
   * Execute remove grocery from storage unit command.
   *
   * @return true if commands should be shown to user again, false if not
   */
  @Override
  public Boolean execute() {
    TableData tableData = storageUnit.toTableData();
    displayManager.printTable(tableData);
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
    displayManager.showColoredMessage("Grocery removed successfully", Color.GREEN);
    return false;

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
