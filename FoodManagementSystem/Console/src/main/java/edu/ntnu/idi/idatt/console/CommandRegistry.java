package edu.ntnu.idi.idatt.console;


import edu.ntnu.idi.idatt.console.exceptions.UnknownCommandException;
import edu.ntnu.idi.idatt.console.exceptions.UnknownContextException;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandRegistry is responsible for managing, executing and switching menu-contexts. Each context
 * has commands which will be handled on user interaction. CommandRegistry makes sure that the right
 * command is being executed.
 */
public class CommandRegistry {

  private final Map<String, MenuContext> contexts;
  private MenuContext currentContext;

  /**
   * Public Constructor for command registry.
   */
  public CommandRegistry() {
    this.contexts = new HashMap<>();
  }

  /**
   * Add new menu context to CommandRegistry.
   *
   * @param context MenuContext with defined commands
   */
  public void addContext(MenuContext context) {
    contexts.put(context.getKey(), context);
  }

  /**
   * Switches current context and renders context's command.
   *
   * @param contextKey context to switch to
   */
  public void switchContext(String contextKey) {
    MenuContext context = contexts.get(contextKey);
    if (context == null) {
      throw new UnknownContextException("Unknown context: " + contextKey);
    }
    currentContext = context;
    context.displayMenu();
  }

  /**
   * Check if command exists in current context and execute it.
   *
   * @param keyword user command input
   * @return Boolean rerender menu-context commands.
   */
  public Boolean executeCommand(String keyword) {
    Command command = currentContext.getCommand(keyword);
    if (command == null) {
      throw new UnknownCommandException(
          "Unknown command: " + keyword + " - type 'help' for available commands");
    }
    return command.execute();
  }

  /**
   * Get current active menu context.
   *
   * @return MenuContext return application's current menu-context
   */
  public MenuContext getCurrentContext() {
    return currentContext;
  }
}
