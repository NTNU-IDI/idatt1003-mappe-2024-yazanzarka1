package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.units.Kilogram;
import edu.ntnu.idi.idatt.units.Liter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecipeTest {

  Recipe recipe;

  @BeforeEach
  void setUp() {
    this.recipe = new Recipe("Test Recipe", "Test Description");
    recipe.addGrocery(new Grocery("Test Grocery", new Kilogram(), 200), 1.0f);
    recipe.addGrocery(new Grocery("Test Grocery Liter", new Liter(), 200), 3.0f);


  }

  @Test
  void addGrocery() {
    recipe.addGrocery(new Grocery("Test Grocery 2", new Kilogram(), 200), 1.0f);
    Assertions.assertEquals(3, recipe.getGroceries().size(), "Grocery list should contain 3 items");
  }

  @Test
  @DisplayName("Test Add Grocery Should Increase If Grocery Already Exists")
  void addGroceryShouldIncreaseIfGroceryAlreadyExists() {
    Grocery grocery = new Grocery("Test Grocery6", new Kilogram(), 200);
    recipe.addGrocery(grocery, 1.0f);
    recipe.addGrocery(grocery, 1.0f);

    Assertions.assertEquals(2.0f, recipe.getGrocery(grocery.getGroceryName()).amount(), "Grocery amount should be 2.0");


  }

  @Test
  void getGroceries() {
    Assertions.assertEquals(2, recipe.getGroceries().size(), "Grocery list should contain 2 items");
  }

  @Test
  void getRecipePrice() {
    Assertions.assertEquals(800, recipe.getRecipePrice(), "Recipe price should be 600");
  }

  @Test
  void getName() {
    Assertions.assertEquals("Test Recipe", recipe.getName(), "Recipe name should be Test Recipe");
  }

  @Test
  void getDescription() {
    Assertions.assertEquals("Test Description", recipe.getDescription(),
        "Recipe description should be Test Description");
  }

  @Test
  void getPeopleCount() {
    Assertions.assertEquals(4, recipe.getPeopleCount(), "Recipe should be for 4 people");
  }

  @Test
  void getSteps() {
    recipe = new Recipe("Test Recipe", "Test Description", "Test Steps", 4);
    Assertions.assertEquals("Test Steps", recipe.getSteps(), "Recipe steps should be empty");
  }

  @Test
  @DisplayName("Test Recipe Initialization")
  void testRecipeInitialization() {
    Recipe newRecipe = new Recipe("Test Recipe", "Test Description", "Test Steps", 4);
    Assertions.assertAll(
        () -> Assertions.assertEquals("Test Recipe", newRecipe.getName(),
            "Recipe name should be Test Recipe"),
        () -> Assertions.assertEquals("Test Description", newRecipe.getDescription(),
            "Recipe description should be Test Description"),
        () -> Assertions.assertEquals("Test Steps", newRecipe.getSteps(),
            "Recipe steps should be Test Steps"),
        () -> Assertions.assertEquals(4, newRecipe.getPeopleCount(), "Recipe should be for 4 people")
    );
  }
}