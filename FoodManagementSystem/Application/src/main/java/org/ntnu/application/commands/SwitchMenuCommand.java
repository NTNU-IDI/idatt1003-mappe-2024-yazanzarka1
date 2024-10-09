package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.console.CommandRegistry;

/**
 * Switch menu command to switch to another menu.
 */
public class SwitchMenuCommand implements Command {

  private final String targetMenu;
  private final CommandRegistry commandRegistry;

  /**
   * switch menu command to switch to another menu.
   *
   * @param targetMenu      Menu to switch to
   * @param commandRegistry Command registry to handle context switching
   */
  public SwitchMenuCommand(String targetMenu, CommandRegistry commandRegistry) {
    this.targetMenu = targetMenu;
    this.commandRegistry = commandRegistry;
  }

  /**
   * execute switch menu command.
   *
   * @return true if commands should be shown to user again, false if not
   */
  @Override
  public Boolean execute() {
    commandRegistry.switchContext(targetMenu);
    return false;
  }

  /**
   * description of switch menu command.
   *
   * @return Description of switch menu command
   */
  @Override
  public String getDescription() {
    return String.format("Go to %s", targetMenu);
  }
}
