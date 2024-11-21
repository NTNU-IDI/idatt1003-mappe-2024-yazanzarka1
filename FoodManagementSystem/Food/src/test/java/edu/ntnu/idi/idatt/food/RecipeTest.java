package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
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

    Assertions.assertEquals(2.0f, recipe.getGrocery(grocery.getGroceryName()).amount(),
        "Grocery amount should be 2.0");


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
        () -> Assertions.assertEquals(4, newRecipe.getPeopleCount(),
            "Recipe should be for 4 people")
    );
  }

  @Test
  @DisplayName("Test Adding a Grocery with Invalid Amount")
  void addGroceryWithInvalidAmountShouldThrowException() {
    Grocery invalidGrocery = new Grocery("Invalid Grocery", new Kilogram(), 100);
    Assertions.assertThrows(IllegalArgumentException.class, () -> recipe.addGrocery(invalidGrocery, -1.0f),
        "Adding a grocery with negative amount should throw an exception");
  }


  @Test
  @DisplayName("Test Get Grocery That Doesn't Exist")
  void getNonExistentGroceryShouldReturnNull() {
    RecipeGrocery nonExistentGrocery = recipe.getGrocery("Non-existent Grocery");
    Assertions.assertNull(nonExistentGrocery, "Getting a non-existent grocery should return null");
  }

  @Test
  @DisplayName("Test Recipe Price With No Groceries")
  void getRecipePriceWithNoGroceriesShouldReturnZero() {
    Recipe emptyRecipe = new Recipe("Empty Recipe", "No Groceries Here", "No Steps Added", 2);
    Assertions.assertEquals(0.0f, emptyRecipe.getRecipePrice(), "Recipe price with no groceries should be 0.0");
  }

  @Test
  @DisplayName("Test Adding Grocery with Large Amount")
  void addGroceryWithLargeAmount() {
    Grocery largeGrocery = new Grocery("Large Grocery", new Kilogram(), 50);
    recipe.addGrocery(largeGrocery, 1000.0f);
    Assertions.assertEquals(1000.0f, recipe.getGrocery(largeGrocery.getGroceryName()).amount(),
        "The grocery amount should correctly reflect the large addition");
  }

  @Test
  @DisplayName("Test Recipe Initialization with Invalid Name")
  void testInvalidNameInitialization() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Recipe("", "Valid Description", "Valid Steps", 4),
        "Initializing a recipe with an invalid name should throw an exception");
  }

  @Test
  @DisplayName("Test Recipe Initialization with Invalid Description")
  void testInvalidDescriptionInitialization() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Recipe("Valid Name", "", "Valid Steps", 4),
        "Initializing a recipe with an invalid description should throw an exception");
  }

  @Test
  @DisplayName("Test Recipe Initialization with Invalid Steps")
  void testInvalidStepsInitialization() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Recipe("Valid Name", "Valid Description", "", 4),
        "Initializing a recipe with invalid steps should throw an exception");
  }

  @Test
  @DisplayName("Test Recipe Initialization with Invalid People Count")
  void testInvalidPeopleCountInitialization() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Recipe("Valid Name", "Valid Description", "Valid Steps", 0),
        "Initializing a recipe with an invalid people count should throw an exception");
  }

  @Test
  @DisplayName("Test Adding Grocery with Null Grocery")
  void addNullGroceryShouldThrowException() {
    Assertions.assertThrows(NullPointerException.class,
        () -> recipe.addGrocery(null, 1.0f),
        "Adding a null grocery should throw an exception");
  }

  @Test
  @DisplayName("Test Recipe Equality")
  void testRecipeEquality() {
    Recipe identicalRecipe = new Recipe("Test Recipe", "Test Description");
    Assertions.assertEquals(recipe.getName(), identicalRecipe.getName(),
        "Recipes with the same name should be considered equal by name");
    Assertions.assertEquals(recipe.getDescription(), identicalRecipe.getDescription(),
        "Recipes with the same description should be considered equal by description");
  }
}