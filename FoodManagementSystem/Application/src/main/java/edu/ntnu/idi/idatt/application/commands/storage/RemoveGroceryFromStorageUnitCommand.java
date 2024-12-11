package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.FloatValidator;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.food.constants.StorageUnitConstants;
import java.util.List;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Remove grocery from storage unit.
 *
 * @see Command
 * @see StorageUnit
 */
public class RemoveGroceryFromStorageUnitCommand implements Command {

  final InputHandler inputHandler;
  final StorageUnit storageUnit;
  final DisplayManager displayManager;

  /**
   * Initiate a remove grocery from storage unit command.
   *
   * @param storageUnit    Storage unit to remove grocery from
   * @param displayManager Display manager to display messages
   * @param inputHandler   Input handler to get input from user
   */
  public RemoveGroceryFromStorageUnitCommand(StorageUnit storageUnit, DisplayManager displayManager,
      InputHandler inputHandler) {
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
    // Display all groceries in storage unit
    TableData tableData = storageUnit.toTableData();
    displayManager.printTable(tableData);

    displayManager.showColoredMessage(
        String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
        Ansi.Color.YELLOW);

    // Get grocery name from user
    String storageEntryName = inputHandler.getString("Enter name of grocery to remove: ",
        new StringValidator("Invalid grocery name", GroceryConstants.MIN_GROCERY_NAME_LENGTH,
            GroceryConstants.MAX_GROCERY_NAME_LENGTH));

    // Find grocery in storage unit
    List<StorageEntry> storageEntries = storageUnit.findGrocery(storageEntryName);
    if (storageEntries.isEmpty()) {
      displayManager.showColoredMessage("Error: Grocery not found", Color.RED);
      return true;
    }

    // If multiple groceries found, ask user to specify
    if (storageEntries.size() > 1) {
      throw new UnsupportedOperationException(
          "Multiple groceries matched. Specify which one to remove.");
    }

    // Get quantity to remove from user
    float quantity = inputHandler.getFloat("Enter quantity to remove: ", new FloatValidator(
        String.format("Invalid quantity. Quantity should be between %s and %s",
            StorageUnitConstants.MIN_QUANTITY, StorageUnitConstants.MAX_QUANTITY),
        StorageUnitConstants.MIN_QUANTITY, StorageUnitConstants.MAX_QUANTITY));

    // Remove grocery from storage unit
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
    return String.format("Remove grocery from %s", storageUnit.getName().toLowerCase());
  }
}
