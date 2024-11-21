package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.StorageUnit;

/**
 * Display stored groceries in a storageUnit.
 */
public class DisplayStoredGroceryCommand implements Command {

  private final StorageUnit storageUnit;
  private final DisplayManager displayManager;

  /**
   * Initiate command with a given storageUnit.
   *
   * @param storageUnit storageUnit where groceries are stored.
   */
  public DisplayStoredGroceryCommand(StorageUnit storageUnit, DisplayManager displayManager) {
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;

  }

  @Override
  public Boolean execute() {
    TableData tableData = storageUnit.toTableData();
    displayManager.printTable(tableData);
    return false;
  }

  @Override
  public String getDescription() {
    return "Display stored grocery command";
  }
}
