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

	/**
	 *
	 * @return String Menu-Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Unique Key
	 * @return String Key used to identify context in Application lifecycle.
	 *
	 */
	public String getKey() {
		return menuKey;
	}

	/**
	 * @param keyword Command key - user's input should match keyword to execute command
	 * @param command Command Object
	 */
	public void addCommand(String keyword, Command command) {
		commands.put(keyword, command);
	}

	/**
	 * @param keyword get command rom commands map
	 * @return Command
	 */
	public Command getCommand(String keyword) {
		return commands.get(keyword);
	}

	/**
	 * Display commands in current menu-context
	 */
	public void displayMenu() {
		displayManager.showSpace();
		System.out.println("============ " + name + " ============");
		for (String keyword : commands.keySet()) {
			System.out.println(keyword + ": " + commands.get(keyword).getDescription());
		}
		displayManager.showSpace();
	}
}
