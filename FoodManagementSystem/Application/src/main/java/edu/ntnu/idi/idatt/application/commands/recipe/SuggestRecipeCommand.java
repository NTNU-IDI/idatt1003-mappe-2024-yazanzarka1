package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.RecipeSuggestionProvider;
import edu.ntnu.idi.idatt.food.SuggestedRecipe;
import java.util.List;

/**
 * Suggest recipe command.
 */
public class SuggestRecipeCommand implements Command {

  RecipeSuggestionProvider recipeSuggestionProvider;
  DisplayManager displayManager;

  /**
   * Initiate command.
   *
   * @param recipeSuggestionProvider Recipe suggestion provider to suggest recipes
   * @param displayManager Display manager to display messages
   */
  public SuggestRecipeCommand(RecipeSuggestionProvider recipeSuggestionProvider, DisplayManager displayManager) {
    this.recipeSuggestionProvider = recipeSuggestionProvider;
    this.displayManager = displayManager;
  }

  @Override
  public Boolean execute() {

    List<SuggestedRecipe> suggestedRecipiesList = recipeSuggestionProvider.suggestRecipe();
    displayManager.showSpace();
    displayManager.printTable(List.of("Recipe name", "Description", "Price", "People"),
        suggestedRecipiesList.stream().map(
            suggestedRecipe -> List.of(suggestedRecipe.recipe().getName(),
                suggestedRecipe.recipe().getDescription(),
                String.format("%.2f NOK", suggestedRecipe.recipe().getRecipePrice()),
                String.format("%d", suggestedRecipe.recipe().getPeopleCount()))).toList());

    displayManager.showSpace();
    displayManager.showMessage(
        "The suggested recipes are based on the groceries you have in your storage unit.");
    displayManager.showMessage("The recipes are sorted by best before date of the groceries.");
    return false;
  }

  @Override
  public String getDescription() {
    return "Suggest recipes";
  }

}
