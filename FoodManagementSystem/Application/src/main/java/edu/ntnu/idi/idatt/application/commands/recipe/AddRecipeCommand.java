package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.validators.FloatValidator;
import edu.ntnu.idi.idatt.console.validators.IntegerValidator;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeGrocery;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.food.constants.RecipeConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.fusesource.jansi.Ansi;

/**
 * Command to add a recipe to the application.
 *
 * @see Command
 * @see Recipe
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
   * @param inputHandler   InputHandler to handle user input.
   * @param displayManager DisplayManager to display messages.
   */
  public AddRecipeCommand(RecipeManager recipeManager, GroceryManager groceryManager,
      InputHandler inputHandler, DisplayManager displayManager) {
    this.displayManager = displayManager;
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

    displayManager.showColoredMessage(
        String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
        Ansi.Color.YELLOW);

    // Get recipe name and description from user
    String recipeName = inputHandler.getString("Enter recipe name (3 - 50 characters): ",
        new StringValidator("Recipe name should be between 3 and 50 characters",
            RecipeConstants.MIN_RECIPE_NAME_LENGTH, RecipeConstants.MAX_RECIPE_NAME_LENGTH));

    String recipeDescription =
        inputHandler.getString("Enter recipe description(10 - 100 characters): ",
            new StringValidator("Description should be between 10 and 100 characters",
                RecipeConstants.MIN_RECIPE_DESCRIPTION_LENGTH,
                RecipeConstants.MAX_RECIPE_DESCRIPTION_LENGTH));

    // Display available groceries
    displayManager.printTable(groceryManager.toTableData());
    while (true) {
      // get grocery name from user
      String groceryName = inputHandler.getString("Enter grocery name: ",
          new StringValidator("Grocery name should be between 1 and 25 characters",
              GroceryConstants.MIN_GROCERY_NAME_LENGTH, GroceryConstants.MAX_GROCERY_NAME_LENGTH));

      // search for grocery
      Grocery grocery = groceryManager.getAvailableGroceries().stream()
          .filter(g -> g.getGroceryName().equalsIgnoreCase(groceryName))
          .findFirst().orElse(null);

      // if grocery not found display message and continue
      if (grocery == null) {
        displayManager.showMessage("Grocery not found");
        continue;
      }

      // display grocery found message
      displayManager.showMessage("Grocery found: " + grocery.getGroceryName());

      // add grocery to recipe with amount and ask for more groceries
      float amount = inputHandler.getFloat(
          String.format("Enter amount of %s (%.2f - %.2f): ", grocery.getGroceryName(),
              RecipeConstants.MIN_RECIPE_AMOUNT, RecipeConstants.MAX_RECIPE_AMOUNT),
          new FloatValidator(String.format("Amount should be between %.2f, %.2f",
              RecipeConstants.MIN_RECIPE_AMOUNT, RecipeConstants.MAX_RECIPE_AMOUNT),
              RecipeConstants.MIN_RECIPE_AMOUNT, RecipeConstants.MAX_RECIPE_AMOUNT));

      // add grocery to recipe
      groceries.add(new RecipeGrocery(grocery, amount));
      // ask user if they want to add more groceries
      String more = inputHandler.getString("Add more groceries? (y/n): ",
          new StringValidator("Invalid input", 1, 1));
      if (more.equals("n")) {
        break;
      }
    }

    // get recipe steps and people count from user
    String recipeSteps =
        formatSteps(
            inputHandler.getString(
                String.format("Enter recipe steps seperated by (comma) (%s - %s characters): \n",
                    RecipeConstants.MIN_RECIPE_STEPS_LENGTH,
                    RecipeConstants.MAX_RECIPE_STEPS_LENGTH),
                new StringValidator("Invalid input", RecipeConstants.MIN_RECIPE_STEPS_LENGTH,
                    RecipeConstants.MAX_RECIPE_STEPS_LENGTH)));

    int peopleCount = inputHandler.getInt("Enter number of people (1-11): ",
        new IntegerValidator("Invalid input", RecipeConstants.MIN_RECIPE_PEOPLE_COUNT,
            RecipeConstants.MAX_RECIPE_PEOPLE_COUNT));

    // create new recipe and add groceries
    Recipe newRecipe =
        new Recipe(recipeName, recipeDescription, recipeSteps, peopleCount);
    for (RecipeGrocery recipeGrocery : groceries) {
      newRecipe.addGrocery(recipeGrocery.grocery(), recipeGrocery.amount());
    }
    // add recipe to recipe manager
    recipeManager.addRecipe(newRecipe);
    displayManager.showMessage("Recipe added: " + recipeName);
    return false;
  }

  private String formatSteps(String recipeSteps) {
    return Arrays.stream(recipeSteps.trim().split(",")).map(String::trim)
        .reduce((s1, s2) -> s1 + "\n" + s2).orElse("");

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
