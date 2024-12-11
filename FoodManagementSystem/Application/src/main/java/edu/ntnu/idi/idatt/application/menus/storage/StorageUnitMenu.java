package edu.ntnu.idi.idatt.application.menus.storage;

import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.application.commands.storage.AddGroceryToStorageUnitCommand;
import edu.ntnu.idi.idatt.application.commands.storage.DisplayExpiredGroceriesCommand;
import edu.ntnu.idi.idatt.application.commands.storage.DisplayGroceriesInStorageUnitBeforeDateCommand;
import edu.ntnu.idi.idatt.application.commands.storage.DisplayStoredGroceryCommand;
import edu.ntnu.idi.idatt.application.commands.storage.RemoveGroceryFromStorageUnitCommand;
import edu.ntnu.idi.idatt.application.commands.storage.SearchInStorageUnitCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
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
   * @param displayManager  Display manager to display messages
   * @param inputHandler    Input handler to handle user input
   */
  public StorageUnitMenu(CommandRegistry commandRegistry, GroceryManager groceryManager,
      StorageUnit storageUnit, DisplayManager displayManager, InputHandler inputHandler) {
    super("Storage Menu", "storage-menu");
    addCommand("1", new DisplayStoredGroceryCommand(storageUnit, displayManager));
    addCommand("2", new AddGroceryToStorageUnitCommand(groceryManager, storageUnit, displayManager,
        inputHandler));
    addCommand("3",
        new RemoveGroceryFromStorageUnitCommand(storageUnit, displayManager, inputHandler));
    addCommand("4", new SearchInStorageUnitCommand(storageUnit, displayManager, inputHandler));
    addCommand("5", new DisplayExpiredGroceriesCommand(storageUnit, displayManager));
    addCommand("6", new DisplayGroceriesInStorageUnitBeforeDateCommand(storageUnit, displayManager,
        inputHandler));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));

  }
}
