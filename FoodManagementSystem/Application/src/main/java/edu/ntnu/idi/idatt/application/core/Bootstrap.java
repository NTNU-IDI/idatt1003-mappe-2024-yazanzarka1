package edu.ntnu.idi.idatt.application.core;

import edu.ntnu.idi.idatt.application.menus.grocery.GroceryMenu;
import edu.ntnu.idi.idatt.application.menus.main.MainMenu;
import edu.ntnu.idi.idatt.application.menus.recipes.RecipesMenu;
import edu.ntnu.idi.idatt.application.menus.storage.StorageUnitMenu;
import edu.ntnu.idi.idatt.console.CommandRegistry;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.RecipeSuggestionProvider;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.units.UnitProvider;

/**
 * Bootstraps CommandRegistry and registers MenuContexts.
 */
public class Bootstrap {


  /**
   * Initiate CommandRegistry.
   *
   * @return CommandRegistry initialized command registry
   */
  public static CommandRegistry initCommandRegistry(DisplayManager displayManager,
      InputHandler inputHandler, UnitProvider unitProvider) {
    CommandRegistry commandRegistry = new CommandRegistry();

    // Create and register menus
    // Register Main Menu's context and add to registry.
    MainMenu mainMenu = new MainMenu(commandRegistry);
    commandRegistry.addContext(mainMenu);

    // Register Grocery Menu's context and add to registry.
    GroceryManager groceryManager = new GroceryManager();
    GroceryMenu groceryMenu = new GroceryMenu(commandRegistry, groceryManager, unitProvider, displayManager, inputHandler);
    commandRegistry.addContext(groceryMenu);

    // Register Storage Menu's context and add to registry
    StorageUnit mainStorageUnit = new StorageUnit("Main Storage Unit");
    StorageUnitMenu storageUnitMenu = new StorageUnitMenu(commandRegistry, groceryManager,
        mainStorageUnit, displayManager, inputHandler);
    commandRegistry.addContext(storageUnitMenu);

    // Register Recipes Menu's context and add to registry
    RecipeManager recipeManager = new RecipeManager("Cook Book");
    RecipeSuggestionProvider recipeSuggestionProvider =
        new RecipeSuggestionProvider(recipeManager, mainStorageUnit);

    RecipesMenu recipesMenu =
        new RecipesMenu(commandRegistry, recipeManager, mainStorageUnit, groceryManager,
            recipeSuggestionProvider, displayManager, inputHandler);
    commandRegistry.addContext(recipesMenu);

    // Seed data
    Seeder seeder = new Seeder(groceryManager, mainStorageUnit, recipeManager);
    boolean seedData = new InputHandler().getInput(
            "write 'y' to seed data, anything else to skip: ")
        .equalsIgnoreCase("y");
    if (seedData) {
      seeder.seed();
    }

    // Set initial menu context
    commandRegistry.switchContext("main-menu");

    return commandRegistry;
  }

}
