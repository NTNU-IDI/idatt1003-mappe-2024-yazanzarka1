package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SuggestedRecipeTest {

  SuggestedRecipe suggestedRecipe;
  Recipe recipe;
  Float score = 5.0f;


  @BeforeEach
  void setUp() {
    recipe = new Recipe("Test Recipe", "Test Description");
    suggestedRecipe = new SuggestedRecipe(recipe, score);
  }

  @Test
  @DisplayName("Test Suggested Recipe recipe getter")
  void recipe() {
    assertEquals(recipe, suggestedRecipe.recipe(), "Recipe should be Test Recipe");
  }

  @Test
  @DisplayName("Test Suggested Recipe score getter")
  void score() {
    assertEquals(score, suggestedRecipe.score(), "Score should be 5.0f");
  }
}