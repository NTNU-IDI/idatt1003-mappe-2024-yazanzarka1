package edu.ntnu.idi.idatt.food;

/**
 * Represents a suggested recipe record. Contains a recipe and a score.
 * Used in the RecipeManager class.
 *
 * @see Recipe
 * @see RecipeManager
 */
public record SuggestedRecipe(Recipe recipe, float score) {

}
