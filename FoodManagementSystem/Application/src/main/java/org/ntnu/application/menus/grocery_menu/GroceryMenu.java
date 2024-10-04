package org.ntnu.application.menus.grocery_menu;

import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;

public class GroceryMenu extends MenuContext {
	public GroceryMenu(CommandRegistry commandRegistry){
		super("Grocery", "grocery-menu");
		addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
	}
}




