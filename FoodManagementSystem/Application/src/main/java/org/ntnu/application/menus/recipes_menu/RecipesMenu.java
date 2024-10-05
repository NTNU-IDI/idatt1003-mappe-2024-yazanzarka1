package org.ntnu.application.menus.recipes_menu;

import org.ntnu.application.commands.AddGroceryToStorageUnitCommand;
import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.food.GroceryManager;

public class RecipesMenu extends MenuContext {

	public RecipesMenu(CommandRegistry commandRegistry) {
		super("Recipes Menu", "recipes-menu");
		addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
	}

}
