package edu.ntnu.idi.idatt.console;

import java.util.HashMap;
import java.util.Map;
import org.fusesource.jansi.Ansi.Color;


/**
 * MenuContext is responsible for defining commands and displaying current context's commands.
 */
public class MenuContext {

  private final String name;
  private final String menuKey;
  private final Map<String, Command> commands;
  private final DisplayManager displayManager = new DisplayManager();

  /**
   * Initiate menu context.
   *
   * @param name    name of the menu
   * @param menuKey unique menu-key
   */
  public MenuContext(String name, String menuKey) {
    this.name = name;
    this.menuKey = menuKey;
    this.commands = new HashMap<>();
  }

  /**
   * MenuContext's name getter.
   *
   * @return String Menu-Name
   */
  public String getName() {
    return name;
  }

  /**
   * Unique Key.
   *
   * @return String Key used to identify context in Application lifecycle.
   */
  public String getKey() {
    return menuKey;
  }

  /**
   * Adds command to the menu context.
   *
   * @param keyword Command key - user's input should match keyword to execute command
   * @param command Command Object
   */
  public void addCommand(String keyword, Command command) {
    commands.put(keyword, command);
  }

  /**
   * get a command from menu context.
   *
   * @param keyword get command rom commands map
   * @return Command
   */
  public Command getCommand(String keyword) {
    return commands.get(keyword);
  }

  /**
   * Display commands in current menu-context.
   */
  public void displayMenu() {
    displayManager.showSpace();
    displayManager.showColoredMessage("============ " + name + " ============", Color.YELLOW);
    printCommands();
    displayManager.showSpace();
  }

  /**
   * Padding for command-list.
   */
  private String padRight(String string, int n) {
    return String.format("%-" + n + "s", string);
  }

  /**
   * Print all commands in the context. with evenly spaced padding.
   */
  private void printCommands() {
    // Find the longest command keyword to pad the output
    int maxWidth =
        commands.keySet().stream().map(String::length).max(Integer::compareTo).orElse(0);

    // Print each command with its description
    for (String keyword : commands.keySet()) {
      displayManager.showColoredMessage(
          padRight(keyword + ":", maxWidth + 2) + commands.get(keyword).getDescription(),
          Color.CYAN);
    }
  }

}
