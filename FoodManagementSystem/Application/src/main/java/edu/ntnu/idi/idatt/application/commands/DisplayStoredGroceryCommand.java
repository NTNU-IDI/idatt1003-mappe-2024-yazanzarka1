package edu.ntnu.idi.idatt.application.commands;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.food.StorageUnit;

/**
 * Display stored groceries in a storageUnit.
 */
public class DisplayStoredGroceryCommand implements Command {

  final StorageUnit storageUnit;

  /**
   * Initiate command with a given storageUnit.
   *
   * @param storageUnit storageUnit where groceries are stored.
   */
  public DisplayStoredGroceryCommand(StorageUnit storageUnit) {
    this.storageUnit = storageUnit;

  }

  @Override
  public Boolean execute() {
    storageUnit.displayGroceries();
    return false;
  }

  @Override
  public String getDescription() {
    return "Display stored grocery command";
  }
}
