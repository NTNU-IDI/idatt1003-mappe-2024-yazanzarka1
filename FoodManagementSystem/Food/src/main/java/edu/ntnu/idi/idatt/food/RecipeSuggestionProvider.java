package edu.ntnu.idi.idatt.food;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Suggest a recipe based on groceries in storage unit. This class is responsible for suggesting a
 * recipe based on the groceries in the storage unit. It uses the RecipeManager to get all recipes
 * and the StorageUnit to get all groceries in the storage unit. Suggestion are then sorted based on
 * a score that is calculated with respect to Best Before Date.
 */
public class RecipeSuggestionProvider {

  RecipeManager recipeManager;
  StorageUnit storageUnit;

  /**
   * Create a new RecipeSuggestionProvider.
   *
   * @param recipeManager RecipeManager to get all recipes
   * @param storageUnit   StorageUnit to get all groceries in storage unit
   */
  public RecipeSuggestionProvider(RecipeManager recipeManager, StorageUnit storageUnit) {
    this.recipeManager = recipeManager;
    this.storageUnit = storageUnit;
  }

  /**
   * Suggest a recipe based on groceries in storage unit.
   *
   * @return Recipe a recipe that can be cooked with the groceries in the storage unit
   */
  public List<SuggestedRecipe> suggestRecipe() {
    HashMap<Recipe, Float> recipeScores = new HashMap<>();
    for (Recipe recipe : recipeManager.getRecipes()) {
      float score = 0;

      // Skip recipes that do not have any groceries
      if (recipe.getGroceries().isEmpty()) {
        continue;
      }

      boolean hasAllGroceries = true;
      for (RecipeGrocery recipeGrocery : recipe.getGroceries().values()) {
        if (storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName()) != null) {
          StorageEntry storageEntry =
              storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());
          score += calculateScore(storageEntry, recipeGrocery);
        } else {
          hasAllGroceries = false;
          break;
        }

      }
      if (hasAllGroceries) {
        recipeScores.put(recipe, score);
      }
    }

    return recipeScores.entrySet().stream()
        .sorted((r1, r2) -> r2.getValue().compareTo(r1.getValue()))
        .map(e -> new SuggestedRecipe(e.getKey(), e.getValue())).toList();
  }

  private float calculateScore(StorageEntry storageEntry, RecipeGrocery recipeGrocery) {
    float score = 0;
    float deltaBestBeforeDays = TimeUnit.MILLISECONDS.toDays(
        storageEntry.getBestBeforeDate().getTime() - new Date().getTime());

    /* if best before date is getting close,
       the score should be higher so it is cooked before it expires.
     * if best before date is passed,
       the score should be first positive and decrease as it gets further away.
     * Best before does not mean that the grocery is bad,
       but it is a good indicator of when it is best to use it
     */
    score += calculateBestBeforeScore(deltaBestBeforeDays);
    score += 25 * recipeGrocery.amount();
    return score;
  }

  private float calculateBestBeforeScore(float deltaBestBeforeDays) {
    if (deltaBestBeforeDays > 0) {
      // if best before date is getting close, the score should be higher so it is cooked before it expires
      return -78 * deltaBestBeforeDays + 877;
    } else {
      // if best before date is passed, the score should be first positive and decrease as it gets further away
      return (float) (300.0 * deltaBestBeforeDays + 1000);
    }
  }

}
