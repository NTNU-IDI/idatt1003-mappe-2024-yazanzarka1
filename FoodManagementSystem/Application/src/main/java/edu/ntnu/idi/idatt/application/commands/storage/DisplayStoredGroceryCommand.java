package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.StorageUnit;
import org.fusesource.jansi.Ansi.Color;

/**
 * Display stored groceries in a ٍٍStorageUnit.
 *
 * @see Command
 * @see StorageUnit
 */
public class DisplayStoredGroceryCommand implements Command {

  private final StorageUnit storageUnit;
  private final DisplayManager displayManager;

  /**
   * Initiate command with a given storageUnit.
   *
   * @param storageUnit storageUnit where groceries are stored.
   * @param displayManager DisplayManager to display messages.
   */
  public DisplayStoredGroceryCommand(StorageUnit storageUnit, DisplayManager displayManager) {
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;

  }

  @Override
  public Boolean execute() {
    TableData tableData = storageUnit.toTableData();

    displayManager.printTable(storageUnit.getName(), tableData);
    displayManager.showColoredMessage(String.format("Total Value: %.2f NOK", storageUnit.getTotalValue()), Color.CYAN);
    return false;
  }

  @Override
  public String getDescription() {
    return String.format("Display groceries in your %s", storageUnit.getName().toLowerCase());
  }
}
