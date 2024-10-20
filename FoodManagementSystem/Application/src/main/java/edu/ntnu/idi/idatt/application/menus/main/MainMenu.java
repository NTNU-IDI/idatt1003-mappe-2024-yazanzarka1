package edu.ntnu.idi.idatt.application.menus.main;

import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.MenuContext;

/**
 * Main menu. First menu outputted to user.
 */
public class MainMenu extends MenuContext {

  /**
   * Initiate main menu.
   *
   * @param commandRegistry commandRegistry to handle context switching.
   */
  public MainMenu(CommandRegistry commandRegistry) {
    super("Main Menu", "main-menu");
    addCommand("1", new SwitchMenuCommand("grocery-menu", commandRegistry));
    addCommand("2", new SwitchMenuCommand("storage-menu", commandRegistry));
    addCommand("3", new SwitchMenuCommand("recipes-menu", commandRegistry));

  }
}




