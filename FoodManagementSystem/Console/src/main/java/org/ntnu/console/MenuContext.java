package org.ntnu.console;

import java.util.HashMap;
import java.util.Map;


/**
 * MenuContext is responsible for defining commands and displaying current context's commands
 */
public class MenuContext {

	private final String name;
	private final String menuKey;
	private final Map<String, Command> commands;
	private final DisplayManager displayManager = new DisplayManager();

	public MenuContext(String name, String menuKey) {
		this.name = name;
		this.menuKey = menuKey;
		this.commands = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return menuKey;
	}

	public void addCommand(String keyword, Command command) {
		commands.put(keyword, command);
	}

	public Command getCommand(String keyword) {
		return commands.get(keyword);
	}

	public void displayMenu() {
		displayManager.showSpace();
		System.out.println("============ " + name + " ============");
		for (String keyword : commands.keySet()) {
			System.out.println(keyword + ": " + commands.get(keyword).getDescription());
		}
		displayManager.showSpace();
	}
}
