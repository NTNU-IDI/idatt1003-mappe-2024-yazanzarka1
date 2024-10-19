package edu.ntnu.idi.idatt.application.menus.groceryMenu;

import edu.ntnu.idi.idatt.application.commands.AddGroceryCommand;
import edu.ntnu.idi.idatt.application.commands.DisplayGroceriesCommand;
import edu.ntnu.idi.idatt.application.commands.RemoveGroceryFromGroceryManagerCommand;
import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.MenuContext;
import edu.ntnu.idi.idatt.food.GroceryManager;

/**
 * Grocery menu context.
 */
public class GroceryMenu extends MenuContext {

  /**
   * Initiate Menu.
   *
   * @param commandRegistry commandRegistry to handle switching contexts
   * @param groceryManager  GroceryManager to execute needed actions related to GroceryManager
   */
  public GroceryMenu(CommandRegistry commandRegistry, GroceryManager groceryManager) {
    super("Grocery", "grocery-menu");
    addCommand("1", new DisplayGroceriesCommand(groceryManager));
    addCommand("2", new AddGroceryCommand(groceryManager));
    addCommand("3", new RemoveGroceryFromGroceryManagerCommand(groceryManager));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
  }
}




