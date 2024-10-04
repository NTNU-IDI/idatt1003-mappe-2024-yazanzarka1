package org.ntnu.application.menus.grocery_menu;

import org.ntnu.application.commands.AddGroceryCommand;
import org.ntnu.application.commands.DisplayGroceriesCommand;
import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.food.StorageContainer;

public class GroceryMenu extends MenuContext {
	public GroceryMenu(CommandRegistry commandRegistry, StorageContainer storageContainer){
		super("Grocery", "grocery-menu");
		addCommand("1", new DisplayGroceriesCommand(storageContainer));
		addCommand("2", new AddGroceryCommand(storageContainer));
		addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
	}
}




