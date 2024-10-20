package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idi.idatt.units.Liter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeGroceryTest {

  RecipeGrocery recipeGrocery;
  Grocery grocery;

  @BeforeEach
  void setUp() {
    grocery = new Grocery("Test Grocery", new Liter(), 50.0f);
    recipeGrocery = new RecipeGrocery(grocery, 3.0f);
  }

  @Test
  void getGrocery() {
    assertEquals(grocery, recipeGrocery.grocery(), "Grocery should be Test Grocery");
  }

  @Test
  void getAmount() {
    assertEquals(3.0f, recipeGrocery.amount(), "Amount should be 3.0f");
  }
}