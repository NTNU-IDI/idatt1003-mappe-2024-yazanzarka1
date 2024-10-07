package org.ntnu.application.menus.recipes_menu;

import org.ntnu.application.commands.DisplayRecipeCommand;
import org.ntnu.application.commands.DisplayRecipesCommand;
import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.food.RecipeManager;

public class RecipesMenu extends MenuContext {

	public RecipesMenu(CommandRegistry commandRegistry, RecipeManager recipeManager) {
		super("Recipes Menu", "recipes-menu");
		addCommand("1", new DisplayRecipesCommand(recipeManager));
		addCommand("2", new DisplayRecipeCommand(recipeManager));
		addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
	}

}
