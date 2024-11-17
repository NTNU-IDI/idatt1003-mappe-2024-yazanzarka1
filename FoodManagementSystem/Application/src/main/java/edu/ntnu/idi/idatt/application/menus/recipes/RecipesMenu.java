package edu.ntnu.idi.idatt.application.menus.recipes;

import edu.ntnu.idi.idatt.application.commands.SwitchMenuCommand;
import edu.ntnu.idi.idatt.application.commands.recipe.AddRecipeCommand;
import edu.ntnu.idi.idatt.application.commands.recipe.CookRecipeCommand;
import edu.ntnu.idi.idatt.application.commands.recipe.DisplayRecipeCommand;
import edu.ntnu.idi.idatt.application.commands.recipe.DisplayRecipesCommand;
import edu.ntnu.idi.idatt.application.commands.recipe.RemoveRecipeCommand;
import edu.ntnu.idi.idatt.application.commands.recipe.SuggestRecipeCommand;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.MenuContext;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.RecipeSuggestionProvider;
import edu.ntnu.idi.idatt.food.StorageUnit;

/**
 * Recipes menu in the application.
 */
public class RecipesMenu extends MenuContext {

  /**
   * Initiate a recipes menu.
   *
   * @param commandRegistry          Command registry to handle context switching
   * @param recipeManager            Recipe manager to display recipes
   * @param storageUnit              Storage unit to handle groceries
   * @param groceryManager           Grocery manager to handle groceries
   * @param recipeSuggestionProvider Recipe suggestion provider to suggest recipes
   * @param displayManager           Display manager to display messages
   * @param inputHandler             Input handler to handle user input
   */
  public RecipesMenu(CommandRegistry commandRegistry, RecipeManager recipeManager,
      StorageUnit storageUnit, GroceryManager groceryManager,
      RecipeSuggestionProvider recipeSuggestionProvider, DisplayManager displayManager,
      InputHandler inputHandler) {
    super("Recipes Menu", "recipes-menu");
    addCommand("1", new DisplayRecipesCommand(recipeManager, displayManager));
    addCommand("2",
        new DisplayRecipeCommand(recipeManager, storageUnit, displayManager, inputHandler));
    addCommand("3", new AddRecipeCommand(recipeManager, groceryManager));
    addCommand("4", new RemoveRecipeCommand(recipeManager, displayManager, inputHandler));
    addCommand("5",
        new CookRecipeCommand(recipeManager, storageUnit, displayManager, inputHandler));
    addCommand("6", new SuggestRecipeCommand(recipeSuggestionProvider, displayManager));
    addCommand("back", new SwitchMenuCommand("main-menu", commandRegistry));
  }

}
