package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
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
   * @param storageUnit    Storage unit to search in
   * @param displayManager Display manager to display messages
   * @param inputHandler   Input handler to get input from user
   */
  public SearchInStorageUnitCommand(StorageUnit storageUnit, DisplayManager displayManager,
      InputHandler inputHandler) {
    this.inputHandler = inputHandler;
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;
  }

  /**
   * execute search in storage unit command.
   *
   * @return Boolean rerender the menu-context commands
   */
  @Override
  public Boolean execute() {
    displayManager.showMessage("Search in storage unit");

    String query = inputHandler.getString("Enter grocery name: ",
        new StringValidator("Invalid grocery name", GroceryConstants.MIN_GROCERY_NAME_LENGTH,
            GroceryConstants.MAX_GROCERY_NAME_LENGTH));
    // search in storage unit
    List<StorageEntry> foundEntries = storageUnit.findGrocery(query);
    if (foundEntries.isEmpty()) {
      displayManager.showFancyMessage("No groceries found");
      return false;
    }
    // display search results
    TableData foundEntriesTableData = storageUnit.toTableData(foundEntries);
    displayManager.printTable("Search Results", foundEntriesTableData);
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
