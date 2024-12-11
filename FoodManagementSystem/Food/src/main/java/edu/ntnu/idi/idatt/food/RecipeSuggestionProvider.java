package edu.ntnu.idi.idatt.food;


import static java.lang.Math.abs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Suggest a recipe based on groceries in storage unit. This class is responsible for suggesting a
 * recipe based on the groceries in the storage unit. It uses the RecipeManager to get all recipes
 * and the StorageUnit to get all groceries in the storage unit. Suggestion are then sorted based on
 * a score that is calculated with respect to Best Before Date.
 *
 * @see RecipeManager
 * @see StorageUnit
 */
public class RecipeSuggestionProvider {

  // days before a grocery is considered inedible
  static final int BEST_BEFORE_DAYS_THRESHOLD = 7;
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
   * @see SuggestedRecipe
   */
  public List<SuggestedRecipe> suggestRecipe() {
    HashMap<Recipe, Float> recipeScores = new HashMap<>();

    // For each recipe, calculate a score based on the groceries in the storage unit
    recipeManager.getRecipes().stream().filter(recipe -> !recipe.getGroceries().isEmpty())
        .forEach(recipe -> {
          AtomicReference<Float> score = new AtomicReference<>(0.0f);

          // Calculate the score for the recipe
          boolean hasAllGroceries =
              recipe.getGroceries().values().stream().allMatch(recipeGrocery -> {
                StorageEntry storageEntry =
                    storageUnit.findGroceryByName(recipeGrocery.grocery().getGroceryName());

                if (storageEntry == null || storageEntry.getQuantity() < recipeGrocery.amount()) {
                  // If a grocery is missing or insufficient, return false
                  return false;
                }

                if (storageEntry.isExpired()) {
                  // if the best-before date is passed by more than 4 days, then it's not consumable
                  float deltaBestBeforeDays = abs(TimeUnit.MILLISECONDS.toDays(
                      storageEntry.getBestBeforeDate().getTime() - new Date().getTime()));
                  // grocery is expired, absolute value of days
                  // since expiration is compared to threshold
                  if (deltaBestBeforeDays > BEST_BEFORE_DAYS_THRESHOLD) {
                    return false;
                  }
                }

                // If the grocery is in the storage unit and the quantity is sufficient,
                // calculate the score and continue
                score.accumulateAndGet(calculateScore(storageEntry, recipeGrocery), Float::sum);
                return true;
              });
          // If all groceries are in the storage unit, add the recipe to the list
          if (hasAllGroceries) {
            recipeScores.put(recipe, score.get());
          }
        });

    // Sort the recipes based on the score
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
     * Best before does not mean that the grocery is bad after,
       but it is a good indicator of when it is best to use it
     */
    score += calculateBestBeforeScore(deltaBestBeforeDays);
    score += 25 * recipeGrocery.amount();
    return score;
  }

  private float calculateBestBeforeScore(float deltaBestBeforeDays) {
    if (deltaBestBeforeDays > 0) {
      // if best before date is getting close,
      // the score should be higher so it is cooked before it expires
      return -78 * deltaBestBeforeDays + 877;
    } else {
      // if best before date is passed,
      // the score should be first positive and decrease as it gets further away
      return (float) (300.0 * deltaBestBeforeDays + 1000);
    }
  }

}
