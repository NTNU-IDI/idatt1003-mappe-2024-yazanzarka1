package edu.ntnu.idi.idatt.application.commands;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeGrocery;
import edu.ntnu.idi.idatt.food.RecipeManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Command to add a recipe to the application.
 */
public class AddRecipeCommand implements Command {

  DisplayManager displayManager;
  InputHandler inputHandler;
  RecipeManager recipeManager;
  GroceryManager groceryManager;

  /**
   * Initiate the command with a RecipeManager.
   *
   * @param recipeManager  RecipeManager to add recipe to.
   * @param groceryManager GroceryManager to retrieve available groceries from.
   */
  public AddRecipeCommand(RecipeManager recipeManager, GroceryManager groceryManager) {
    displayManager = new DisplayManager();
    inputHandler = new InputHandler();
    this.recipeManager = recipeManager;
    this.groceryManager = groceryManager;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */

  @Override
  public Boolean execute() {
    List<RecipeGrocery> groceries = new ArrayList<>();
    displayManager.showMessage("Available groceries: ");
    groceryManager.displayGroceries();
    String recipeName = inputHandler.getInput("Enter recipe name: ");
    String recipeDescription = inputHandler.getInput("Enter recipe description: ");
    while (true) {
      int groceryIndex = Integer.parseInt(inputHandler.getInput("Enter grocery index: "));
      Grocery grocery = groceryManager.getAvailableGroceries().get(groceryIndex);
      if (grocery == null) {
        displayManager.showMessage("Grocery not found");
        continue;
      }
      float amount = Float.parseFloat(inputHandler.getInput("Enter amount: "));
      groceries.add(new RecipeGrocery(grocery, amount));
      String more = inputHandler.getInput("Add more groceries? (y/n): ");
      if (more.equals("n")) {
        break;
      }
    }

    Recipe newRecipe = new Recipe(recipeName, recipeDescription);
    for (RecipeGrocery recipeGrocery : groceries) {
      newRecipe.addGrocery(recipeGrocery.grocery(), recipeGrocery.amount());
    }
    recipeManager.addRecipe(newRecipe);
    displayManager.showMessage("Recipe added: " + recipeName);
    return false;
  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Add a recipe";
  }
}
