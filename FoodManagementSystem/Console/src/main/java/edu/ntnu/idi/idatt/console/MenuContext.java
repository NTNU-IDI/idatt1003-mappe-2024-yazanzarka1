package edu.ntnu.idi.idatt.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fusesource.jansi.Ansi.Color;


/**
 * MenuContext is responsible for defining commands and displaying current context's commands.
 * MenuContext is used to group commands together and display them in a menu. MenuContext is used in
 * CommandRegistry. MenuContext is immutable.
 *
 * @author yazanzarka
 * @see CommandRegistry
 * @since 0.0.1
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
   * @return String Key used to identify context in Ui lifecycle.
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
    // add hardcoded commands to the menu
    List<String> commandsList = new ArrayList<>(List.copyOf(commands.keySet()));
    commandsList.add("help");
    commandsList.add("exit");

    // Find the longest command keyword to pad the output
    int maxWidth = commandsList.stream().map(String::length).max(Integer::compareTo).orElse(0);

    // Print each command with its description
    commands.keySet().forEach(keyword -> displayManager.showColoredMessage(
        padRight(keyword + ":", maxWidth + 2) + commands.get(keyword).getDescription(),
        Color.CYAN));
    displayManager.showColoredMessage(padRight("help:", maxWidth + 2) + "Show this menu",
        Color.CYAN);
    displayManager.showColoredMessage(padRight("exit:", maxWidth + 2) + "Exit the program",
        Color.CYAN);

  }

}
