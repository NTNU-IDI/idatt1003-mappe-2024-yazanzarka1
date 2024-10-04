package org.ntnu.console;


import java.util.HashMap;
import java.util.Map;
import org.fusesource.jansi.Ansi.Color;

/**
 * CommandRegistry is responsible for managing, executing and switching menu-contexts.
 * Each context has commands which will be handled on user interaction.
 * CommandRegistry makes sure that the right command is being executed.
 */
public class CommandRegistry {
	private final Map<String, MenuContext> contexts;
	private MenuContext currentContext;
	private final DisplayManager displayManager;

	public CommandRegistry() {
		displayManager = new DisplayManager();
		this.contexts = new HashMap<>();
	}

	public void addContext(MenuContext context) {
		contexts.put(context.getKey(), context);
	}

	public void switchContext(String contextKey) {
		MenuContext context = contexts.get(contextKey);
		if (context != null) {
			currentContext = context;
			context.displayMenu();
		} else {
			displayManager.showColoredMessage("Context not found!", Color.RED);
		}
	}

	public void executeCommand(String keyword) {
		Command command = currentContext.getCommand(keyword);
		if (command != null) {
			command.execute();
		} else {
			displayManager.showColoredMessage("Invalid command!", Color.RED);
		}
	}

	public MenuContext getCurrentContext() {
		return currentContext;
	}
}
