package edu.ntnu.idi.idatt.food;

/**
 * Represents a grocery in a recipe. Contains a grocery and the amount of the grocery in a recipe.
 * Used in the RecipeManager class.
 *
 * @see Recipe
 */
public record RecipeGrocery(Grocery grocery, float amount) {

}
