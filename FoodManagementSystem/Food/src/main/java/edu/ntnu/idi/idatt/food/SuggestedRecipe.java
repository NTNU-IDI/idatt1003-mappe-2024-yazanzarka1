package edu.ntnu.idi.idatt.food;

/**
 * Represents a suggested recipe record. Contains a recipe and a score. Used in the RecipeManager
 * class.
 *
 * @author yazanzarka
 * @see Recipe
 * @see RecipeManager
 * @since 0.0.2
 */
public record SuggestedRecipe(Recipe recipe, float score) {

}
