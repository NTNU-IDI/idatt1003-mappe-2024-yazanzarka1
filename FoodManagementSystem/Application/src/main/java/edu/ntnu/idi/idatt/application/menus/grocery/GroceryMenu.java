package edu.ntnu.idi.idatt.application.menus.grocery;

import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.application.commands.grocery.AddGroceryCommand;
import edu.ntnu.idi.idatt.application.commands.grocery.DisplayGroceriesCommand;
import edu.ntnu.idi.idatt.application.commands.grocery.RemoveGroceryFromGroceryManagerCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.MenuContext;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.units.UnitProvider;

/**
 * Grocery menu context.
 */
public class GroceryMenu extends MenuContext {

  /**
   * Initiate Menu.
   *
   * @param commandRegistry commandRegistry to handle switching contexts
   * @param groceryManager  GroceryManager to execute needed actions related to GroceryManager
   * @param unitProvider    UnitProvider to provide units
   * @param displayManager  DisplayManager to display messages
   * @param inputHandler    InputHandler to handle user input
   */
  public GroceryMenu(CommandRegistry commandRegistry, GroceryManager groceryManager,
      UnitProvider unitProvider, DisplayManager displayManager, InputHandler inputHandler) {
    super("Grocery", "grocery-menu");
    addCommand("1", new DisplayGroceriesCommand(groceryManager, displayManager));
    addCommand("2",
        new AddGroceryCommand(groceryManager, unitProvider, displayManager, inputHandler));
    addCommand("3",
        new RemoveGroceryFromGroceryManagerCommand(groceryManager, displayManager, inputHandler));
    addCommand("back",
        new SwitchMenuCommand("main-menu", commandRegistry));
  }
}




