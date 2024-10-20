package edu.ntnu.idi.idatt.food;

/**
 * Represents a recipe in a recipe. Contains a recipe and the amount of the recipe in a recipe.
 * Used in the Recipe class.
 *
 * @see Recipe
 */
public record RecipeGrocery(Grocery grocery, float amount) {

}
