package edu.ntnu.idi.idatt.application.menus.storage;

import edu.ntnu.idi.idatt.application.commands.AddGroceryToStorageUnitCommand;
import edu.ntnu.idi.idatt.application.commands.DisplayExpiredGroceriesCommand;
import edu.ntnu.idi.idatt.application.commands.DisplayStoredGroceryCommand;
import edu.ntnu.idi.idatt.application.commands.RemoveGroceryFromStorageUnitCommand;
import edu.ntnu.idi.idatt.application.commands.SearchInStorageUnitCommand;
import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.MenuContext;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.StorageUnit;

/**
 * Storage unit menu in the application.
 */
public class StorageUnitMenu extends MenuContext {

  /**
   * Initiate a storage unit menu.
   *
   * @param commandRegistry Command registry to handle context switching
   * @param groceryManager  Grocery manager to handle groceries
   * @param storageUnit     Storage unit to display groceries
   */
  public StorageUnitMenu(CommandRegistry commandRegistry, GroceryManager groceryManager,
      StorageUnit storageUnit) {
    super("Storage Menu", "storage-menu");
    addCommand("1", new DisplayStoredGroceryCommand(storageUnit));
    addCommand("2", new AddGroceryToStorageUnitCommand(groceryManager, storageUnit));
    addCommand("3", new RemoveGroceryFromStorageUnitCommand(storageUnit));
    addCommand("4", new SearchInStorageUnitCommand(storageUnit));
    addCommand("5", new DisplayExpiredGroceriesCommand(storageUnit));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));

  }
}
