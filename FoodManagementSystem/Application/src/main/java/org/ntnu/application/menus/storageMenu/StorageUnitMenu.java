package org.ntnu.application.menus.storageMenu;

import org.ntnu.application.commands.AddGroceryToStorageUnitCommand;
import org.ntnu.application.commands.DisplayExpiredGroceriesCommand;
import org.ntnu.application.commands.DisplayStoredGroceryCommand;
import org.ntnu.application.commands.RemoveGroceryFromStorageUnitCommand;
import org.ntnu.application.commands.SearchInStorageUnitCommand;
import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.food.GroceryManager;
import org.ntnu.food.StorageUnit;

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
