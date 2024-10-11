package org.ntnu.application.commands;

import java.util.List;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.StorageEntry;
import org.ntnu.food.StorageUnit;

/**
 * Search in storage unit.
 */
public class SearchInStorageUnitCommand implements Command {

  final InputHandler inputHandler;
  final StorageUnit storageUnit;
  final DisplayManager displayManager;

  /**
   * Initiate a search in storage unit command.
   *
   * @param storageUnit Storage unit to search in
   */
  public SearchInStorageUnitCommand(StorageUnit storageUnit) {
    inputHandler = new InputHandler();
    this.storageUnit = storageUnit;
    displayManager = new DisplayManager();
  }

  /**
   * execute search in storage unit command.
   *
   * @return Boolean rerender the menu-context commands
   */
  @Override
  public Boolean execute() {
    System.out.println("Search in storage unit");

    String query = inputHandler.getInput("Enter grocery name: ");
    // search in storage unit
    List<StorageEntry> foundEntries = storageUnit.findGrocery(query);
    if (foundEntries.isEmpty()) {
      displayManager.showFancyMessage("No groceries found");
      return false;
    }
    storageUnit.displayGroceries(foundEntries);
    return false;
  }

  /**
   * description of Command.
   *
   * @return String Description of Command
   */
  @Override
  public String getDescription() {
    return "Search in storage unit";
  }
}
