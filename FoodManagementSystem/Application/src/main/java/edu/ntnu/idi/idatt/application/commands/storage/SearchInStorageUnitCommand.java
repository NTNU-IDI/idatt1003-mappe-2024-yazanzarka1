package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.util.List;

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
    displayManager.showMessage("Search in storage unit");

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
