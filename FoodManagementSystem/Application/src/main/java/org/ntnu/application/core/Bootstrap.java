package org.ntnu.application.core;

import org.ntnu.application.menus.groceryMenu.GroceryMenu;
import org.ntnu.application.menus.mainMenu.MainMenu;
import org.ntnu.application.menus.recipesMenu.RecipesMenu;
import org.ntnu.application.menus.storageMenu.StorageUnitMenu;
import org.ntnu.console.CommandRegistry;
import org.ntnu.console.InputHandler;
import org.ntnu.food.GroceryManager;
import org.ntnu.food.RecipeManager;
import org.ntnu.food.StorageUnit;

/**
 * Bootstraps CommandRegistry and registers MenuContexts.
 */
public class Bootstrap {

  /**
   * Initiate CommandRegistry.
   *
   * @return CommandRegistry initialized command registry
   */
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
    StorageUnitMenu storageUnitMenu = new StorageUnitMenu(commandRegistry, groceryManager,
        mainStorageUnit);
    commandRegistry.addContext(storageUnitMenu);

    // Register Recipes Menu's context and add to registry

    RecipeManager recipeManager = new RecipeManager();
    RecipesMenu recipesMenu = new RecipesMenu(commandRegistry, recipeManager);
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
