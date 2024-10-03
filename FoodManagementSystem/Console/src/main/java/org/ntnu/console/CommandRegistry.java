package org.ntnu.console;


import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
	private final Map<String, MenuContext> contexts;
	private MenuContext currentContext;

	public CommandRegistry() {
		this.contexts = new HashMap<>();
	}

	public void addContext(MenuContext context) {
		contexts.put(context.getName(), context);
	}

	public void switchContext(String contextName) {
		MenuContext context = contexts.get(contextName);
		if (context != null) {
			currentContext = context;
			context.displayMenu();
		} else {
			System.out.println("Context not found!");
		}
	}

	public void executeCommand(String keyword) {
		Command command = currentContext.getCommand(keyword);
		if (command != null) {
			command.execute();
		} else {
			System.out.println("Invalid command!");
		}
	}

	public MenuContext getCurrentContext() {
		return currentContext;
	}
}
