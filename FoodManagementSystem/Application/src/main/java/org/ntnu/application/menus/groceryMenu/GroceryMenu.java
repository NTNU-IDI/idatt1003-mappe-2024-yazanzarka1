package org.ntnu.application.menus.groceryMenu;

import org.ntnu.application.commands.AddGroceryCommand;
import org.ntnu.application.commands.DisplayGroceriesCommand;
import org.ntnu.application.commands.RemoveGroceryCommand;
import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.food.GroceryManager;

/**
 * Grocery menu context.
 */
public class GroceryMenu extends MenuContext {

  /**
   * Initiate Menu.
   *
   * @param commandRegistry commandRegistry to handle switching contexts
   * @param groceryManager GroceryManager to execute needed actions related to GroceryManager
   */
  public GroceryMenu(CommandRegistry commandRegistry, GroceryManager groceryManager) {
    super("Grocery", "grocery-menu");
    addCommand("1", new DisplayGroceriesCommand(groceryManager));
    addCommand("2", new AddGroceryCommand(groceryManager));
    addCommand("3", new RemoveGroceryCommand(groceryManager));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
  }
}




