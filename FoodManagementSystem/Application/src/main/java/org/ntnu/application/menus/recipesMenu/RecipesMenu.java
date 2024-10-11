package org.ntnu.application.menus.recipesMenu;

import org.ntnu.application.commands.DisplayRecipeCommand;
import org.ntnu.application.commands.DisplayRecipesCommand;
import org.ntnu.application.commands.SwitchMenuCommand;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.MenuContext;
import org.ntnu.food.RecipeManager;

/**
 * Recipes menu in the application.
 */
public class RecipesMenu extends MenuContext {

  /**
   * Initiate a recipes menu.
   *
   * @param commandRegistry Command registry to handle context switching
   * @param recipeManager  Recipe manager to display recipes
   */
  public RecipesMenu(CommandRegistry commandRegistry, RecipeManager recipeManager) {
    super("Recipes Menu", "recipes-menu");
    addCommand("1", new DisplayRecipesCommand(recipeManager));
    addCommand("2", new DisplayRecipeCommand(recipeManager));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
  }

}
