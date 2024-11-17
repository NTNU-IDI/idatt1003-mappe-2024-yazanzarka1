package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
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
   * Initiate the command with a RecipeManager.
   *
   * @param recipeManager  RecipeManager to add recipe to.
   * @param groceryManager GroceryManager to retrieve available groceries from.
   * @param inputHandler   InputHandler to handle user input.
   */
  public AddRecipeCommand(RecipeManager recipeManager, GroceryManager groceryManager,
      InputHandler inputHandler) {
    displayManager = new DisplayManager();
    this.inputHandler = inputHandler;
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

    String recipeName = inputHandler.getInput("Enter recipe name: ");
    String recipeDescription = inputHandler.getInput("Enter recipe description: ");
    groceryManager.displayGroceries();
    while (true) {
      String groceryName = inputHandler.getInput("Enter grocery name: ");

      // search for grocery
      Grocery grocery = groceryManager.getAvailableGroceries().stream()
          .filter(g -> g.getGroceryName().toLowerCase().contains(groceryName.toLowerCase()))
          .findFirst().orElse(null);

      // if grocery not found display message and continue
      if (grocery == null) {
        displayManager.showMessage("Grocery not found");
        continue;
      }

      displayManager.showMessage("Grocery found: " + grocery.getGroceryName());

      // add grocery to recipe with amount and ask for more groceries
      float amount;
      try {
        amount = Float.parseFloat(inputHandler.getInput("Enter amount: "));
      } catch (NumberFormatException e) {
        throw new UserInputException("Invalid amount");
      }
      groceries.add(new RecipeGrocery(grocery, amount));
      String more = inputHandler.getInput("Add more groceries? (y/n): ");
      if (more.equals("n")) {
        break;
      }
    }
    String recipeSteps = inputHandler.getInput("Enter recipe steps: ");
    int peopleCount;
    try {
      peopleCount = Integer.parseInt(inputHandler.getInput("Enter number of people: "));
    } catch (NumberFormatException e) {
      throw new UserInputException("Invalid number of people");
    }

    Recipe newRecipe =
        new Recipe(recipeName, recipeDescription, recipeSteps, peopleCount, displayManager);
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
