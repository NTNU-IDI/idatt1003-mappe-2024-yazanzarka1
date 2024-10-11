package org.ntnu.application.menus.mainMenu;

import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;

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




