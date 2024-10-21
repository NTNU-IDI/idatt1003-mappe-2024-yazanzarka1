package edu.ntnu.idi.idatt.application.menus.recipes;

import edu.ntnu.idi.idatt.application.commands.CookRecipeCommand;
import edu.ntnu.idi.idatt.application.commands.DisplayRecipeCommand;
import edu.ntnu.idi.idatt.application.commands.DisplayRecipesCommand;
import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.MenuContext;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.StorageUnit;

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
  public RecipesMenu(CommandRegistry commandRegistry, RecipeManager recipeManager, StorageUnit storageUnit) {
    super("Recipes Menu", "recipes-menu");
    addCommand("1", new DisplayRecipesCommand(recipeManager));
    addCommand("2", new DisplayRecipeCommand(recipeManager, storageUnit));
    addCommand("3", new CookRecipeCommand(recipeManager, storageUnit));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
  }

}
