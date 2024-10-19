package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.units.Kilogram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeManagerTest {

  RecipeManager recipeManager;
  Recipe recipe;

  @BeforeEach
  void setUp() {
    recipeManager = new RecipeManager();
    recipe = new Recipe("Test Recipe", "Test Description");
    recipe.addGrocery(new Grocery("Test Grocery", new Kilogram(), 200), 1.0f);
  }

  @Test
  void addRecipeTest() {
    recipeManager.addRecipe(recipe);

    assertEquals(recipe, recipeManager.getRecipes().getFirst(),
        "Recipe list should contain added recipe");
  }

  @Test
  void removeRecipeTest() {
    recipeManager.addRecipe(recipe);
    recipeManager.removeRecipe(0);
    assertEquals(0, recipeManager.getRecipes().size(), "Recipe list should be empty after removal");
  }

  @Test
  void removeRecipeIndexOutOfBoundsTest() {
    recipeManager.addRecipe(recipe);
    assertThrows(IndexOutOfBoundsException.class, () -> recipeManager.removeRecipe(1),
        "Should throw IndexOutOfBoundsException when index is out of bounds");
  }

  @Test
  void getRecipesTest() {
    recipeManager.addRecipe(recipe);
    assertEquals(1, recipeManager.getRecipes().size(), "Recipe list should contain 1 item");
  }
}