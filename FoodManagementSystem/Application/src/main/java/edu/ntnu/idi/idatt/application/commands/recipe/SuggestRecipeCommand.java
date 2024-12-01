package edu.ntnu.idi.idatt.application.commands.recipe;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.RecipeSuggestionProvider;
import edu.ntnu.idi.idatt.food.SuggestedRecipe;
import java.util.List;

/**
 * Suggest recipe command.
 *
 * @see Command
 * @see RecipeSuggestionProvider
 * @see SuggestedRecipe
 */
public class SuggestRecipeCommand implements Command {

  RecipeSuggestionProvider recipeSuggestionProvider;
  DisplayManager displayManager;

  /**
   * Initiate command.
   *
   * @param recipeSuggestionProvider Recipe suggestion provider to suggest recipes
   * @param displayManager           Display manager to display messages
   */
  public SuggestRecipeCommand(RecipeSuggestionProvider recipeSuggestionProvider,
      DisplayManager displayManager) {
    this.recipeSuggestionProvider = recipeSuggestionProvider;
    this.displayManager = displayManager;
  }

  @Override
  public Boolean execute() {

    List<SuggestedRecipe> suggestedRecipiesList = recipeSuggestionProvider.suggestRecipe();
    displayManager.showSpace();

    // Display the suggested recipes in a table
    List<String> headers = List.of("Recipe name", "Description", "Price", "People");
    List<List<String>> body = suggestedRecipiesList.stream().map(
        suggestedRecipe -> List.of(suggestedRecipe.recipe().getName(),
            suggestedRecipe.recipe().getDescription(),
            String.format("%.2f NOK", suggestedRecipe.recipe().getRecipePrice()),
            String.format("%d", suggestedRecipe.recipe().getPeopleCount()))).toList();
    TableData tableData = new TableData(headers, body);
    displayManager.printTable(tableData);

    displayManager.showSpace();
    displayManager.showMessage(
        "Recipes are sorted based on the expiration date of the groceries in the storage unit");
    return false;
  }

  @Override
  public String getDescription() {
    return "Suggest recipes";
  }

}
