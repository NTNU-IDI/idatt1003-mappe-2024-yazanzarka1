package org.ntnu.application.core;

import org.ntnu.application.containers.MainFoodStorageUnit;
import org.ntnu.application.menus.grocery_menu.GroceryMenu;
import org.ntnu.application.menus.main_menu.MainMenu;
import org.ntnu.application.menus.recipes_menu.RecipesMenu;
import org.ntnu.application.menus.storage_menu.StorageUnitMenu;
import org.ntnu.console.CommandRegistry;
import org.ntnu.food.GroceryManager;
import org.ntnu.food.StorageUnit;

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
		GroceryManager groceryManager = new GroceryManager();
		GroceryMenu groceryMenu = new GroceryMenu(commandRegistry, groceryManager);
		commandRegistry.addContext(groceryMenu);

		// Register Storage Menu's context and add to registry
		StorageUnit mainStorageUnit = new StorageUnit("Main Storage Unit");
		StorageUnitMenu storageUnitMenu = new StorageUnitMenu(commandRegistry, groceryManager, mainStorageUnit);
		commandRegistry.addContext(storageUnitMenu);

		// Register Recipes Menu's context and add to registry
		RecipesMenu recipesMenu = new RecipesMenu(commandRegistry);
		commandRegistry.addContext(recipesMenu);


		// Set initial menu context
		commandRegistry.switchContext("main-menu");
		return commandRegistry;
	}

}
