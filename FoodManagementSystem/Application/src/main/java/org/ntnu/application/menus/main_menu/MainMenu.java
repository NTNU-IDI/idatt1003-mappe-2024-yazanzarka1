package org.ntnu.application.menus.main_menu;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.application.commands.SwitchMenuCommand;

public class MainMenu extends MenuContext {
	public MainMenu(CommandRegistry commandRegistry){
		super("Main Menu", "main-menu");
		addCommand("1", new SwitchMenuCommand("grocery-menu", commandRegistry));
		addCommand("2", new SwitchMenuCommand("storages", commandRegistry));
		addCommand("3", new SwitchMenuCommand("recipes ", commandRegistry));

	}
}




