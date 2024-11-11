package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.TestInputHandler;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.units.Liter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddRecipeCommandTest {

  private final RecipeManager recipeManager = new RecipeManager("Cook Book Test");
  AddRecipeCommand addRecipeCommand;
  private GroceryManager groceryManager;

  @BeforeEach
  void setUp() {
    this.groceryManager = new GroceryManager();
    groceryManager.addGrocery(new Grocery("Milk", new Liter(), 200));
    groceryManager.addGrocery(new Grocery("Egg", new Liter(), 200));
    groceryManager.addGrocery(new Grocery("Flour", new Liter(), 200));
  }

  @Test
  void executeWithValidParameters() {
    addRecipeCommand = new AddRecipeCommand(recipeManager, groceryManager,
        new TestInputHandler(
            new String[] {"Test Recipe", "Description", "Milk", "20", "n", "1. Do this, 2. Do that",
                "4"}));
    addRecipeCommand.execute();

    Assertions.assertEquals(1, recipeManager.getRecipes().size());
    Assertions.assertEquals("Test Recipe", recipeManager.getRecipes().getFirst().getName());
    Assertions.assertEquals("Description", recipeManager.getRecipes().getFirst().getDescription());
    Assertions.assertEquals(20, recipeManager.getRecipes().getFirst().getGrocery("Milk").amount());
    Assertions.assertEquals(4, recipeManager.getRecipes().getFirst().getPeopleCount());
  }

  @Test
  void executeWithInvalidParameters() {
    addRecipeCommand = new AddRecipeCommand(recipeManager, groceryManager,
        new TestInputHandler(
            new String[] {"Test Recipe", "Description", "NotValidGrocery", "20", "n",
                "1. Do this, 2. Do that",
                "4"}));
    Assertions.assertThrows(IllegalStateException.class, addRecipeCommand::execute,
        "Grocery not found. Will retry until user gives a valid grocery");

  }

  @Test
  void getDescription() {
    addRecipeCommand = new AddRecipeCommand(recipeManager, groceryManager);
    Assertions.assertEquals("Add a recipe", addRecipeCommand.getDescription());
  }
}