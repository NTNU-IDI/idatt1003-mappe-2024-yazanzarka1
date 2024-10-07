package org.ntnu.application.commands;

import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.RecipeManager;

public class DisplayRecipeCommand implements Command {

  private final RecipeManager recipeManager;
  private final DisplayManager displayManager;
  private final InputHandler inputHandler;
  public DisplayRecipeCommand(RecipeManager recipeManager){
    this.recipeManager = recipeManager;
    this.displayManager = new DisplayManager();
    this.inputHandler = new InputHandler();
  }

  /**
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    displayManager.showSpace();
    recipeManager.displayRecipes();
    displayManager.showSpace();
    try{
      int recipeIndex = Integer.parseInt(inputHandler.getInput("Enter the index of the recipe: "));
      displayManager.showSpace();
      recipeManager.getRecipes().get(recipeIndex).displayRecipe();
      displayManager.showSpace();

      return false;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
      return true;
    }

  }

  /**
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Display Ingredients of a recipe";
  }
}
