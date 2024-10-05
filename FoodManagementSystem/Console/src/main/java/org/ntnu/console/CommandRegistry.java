package org.ntnu.console;


import java.util.HashMap;
import java.util.Map;
import org.fusesource.jansi.Ansi.Color;

/**
 * CommandRegistry is responsible for managing, executing and switching menu-contexts. Each context
 * has commands which will be handled on user interaction. CommandRegistry makes sure that the right
 * command is being executed.
 */
public class CommandRegistry {

	private final Map<String, MenuContext> contexts;
	private final DisplayManager displayManager;
	private MenuContext currentContext;

	public CommandRegistry() {
		displayManager = new DisplayManager();
		this.contexts = new HashMap<>();
	}

	/**
	 * Add new menu context to CommandRegistry
	 *
	 * @param context MenuContext with defined commands
	 */
	public void addContext(MenuContext context) {
		contexts.put(context.getKey(), context);
	}

	/**
	 * Switches current context and renders context's command
	 *
	 * @param contextKey context to switch to
	 */
	public void switchContext(String contextKey) {
		MenuContext context = contexts.get(contextKey);
		if (context != null) {
			currentContext = context;
			context.displayMenu();
		} else {
			displayManager.showColoredMessage("Context not found!", Color.RED);
		}
	}

	/**
	 * @param keyword user command input
	 * @return Boolean rerender menu-context commands.
	 */
	public Boolean executeCommand(String keyword) {
		Command command = currentContext.getCommand(keyword);
		if (command != null) {
			return command.execute();
		} else {
			displayManager.showColoredMessage("Invalid command!", Color.RED);
		}
		return false;
	}

	public MenuContext getCurrentContext() {
		return currentContext;
	}
}
