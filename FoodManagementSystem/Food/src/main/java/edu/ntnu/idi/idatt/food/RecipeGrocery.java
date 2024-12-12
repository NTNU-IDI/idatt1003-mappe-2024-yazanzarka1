package edu.ntnu.idi.idatt.food;

/**
 * Represents a grocery in a recipe. Contains a grocery and the amount of the grocery in a recipe.
 * Used in the RecipeManager class.
 *
 * @author yazanzarka
 * @see Recipe
 * @since 0.0.1
 */
public record RecipeGrocery(Grocery grocery, float amount) {

}
