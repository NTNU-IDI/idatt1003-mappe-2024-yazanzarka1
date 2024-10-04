package org.ntnu.application.core;

import org.ntnu.application.menus.grocery_menu.GroceryMenu;
import org.ntnu.application.menus.main_menu.MainMenu;
import org.ntnu.console.CommandRegistry;

/**
 * Bootstraps CommandRegistry and registers MenuContexts
 */
public class Bootstrap {

	public static CommandRegistry initCommandRegistry() {
		CommandRegistry commandRegistry = new CommandRegistry();

		// Create and register menus
		// Register Main Menu's context and add to registry.
		MainMenu mainMenu = new MainMenu(commandRegistry);
		commandRegistry.addContext(mainMenu);

		// Register Grocery Menu's context and add to registry.
		GroceryMenu groceryMenu = new GroceryMenu(commandRegistry);
		commandRegistry.addContext(groceryMenu);

		// Set initial menu context
		commandRegistry.switchContext("main-menu");
		return commandRegistry;
	}

}
