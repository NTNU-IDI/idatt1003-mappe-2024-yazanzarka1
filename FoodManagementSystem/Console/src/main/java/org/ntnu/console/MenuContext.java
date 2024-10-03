package org.ntnu.console;

import java.util.HashMap;
import java.util.Map;

public class MenuContext {

	private final String name;
	private final String menuKey;
	private final Map<String, Command> commands;

	public MenuContext(String name, String menuKey) {
		this.name = name;
		this.menuKey = menuKey;
		this.commands = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public String getKey() {return menuKey;}

	public void addCommand(String keyword, Command command) {
		commands.put(keyword, command);
	}

	public Command getCommand(String keyword) {
		return commands.get(keyword);
	}

	public void displayMenu() {
		System.out.println("============ " + name + " ============");
		for (String keyword : commands.keySet()) {
			System.out.println(keyword + ": " + commands.get(keyword).getDescription());
		}
	}
}
