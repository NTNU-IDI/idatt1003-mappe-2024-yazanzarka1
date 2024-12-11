package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.units.Kilogram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecipeManagerTest {

  RecipeManager recipeManager;
  Recipe recipe;

  @BeforeEach
  void setUp() {
    recipeManager = new RecipeManager("Cook Book");
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

    assertThrows(IndexOutOfBoundsException.class, () -> recipeManager.removeRecipe(-1),
        "Should throw IndexOutOfBoundsException when index is out of bounds");
  }

  @Test
  void getRecipesTest() {
    recipeManager.addRecipe(recipe);
    assertEquals(1, recipeManager.getRecipes().size(), "Recipe list should contain 1 item");
  }

  @Test
  @DisplayName("Test Add Null Recipe Throws Exception")
  void addNullRecipeThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> recipeManager.addRecipe(null),
        "Adding a null recipe should throw an IllegalArgumentException");
  }

  @Test
  @DisplayName("Test Empty Recipe Manager Name Throws Exception")
  void emptyRecipeManagerNameThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new RecipeManager(""),
        "Creating a RecipeManager with an empty name should throw an IllegalArgumentException");

    assertThrows(IllegalArgumentException.class, () -> new RecipeManager(null),
        "Creating a RecipeManager with a null name should throw an IllegalArgumentException");
  }

  @Test
  @DisplayName("Test Get Recipes Returns Empty List Initially")
  void getRecipesReturnsEmptyListInitially() {
    assertEquals(0, recipeManager.getRecipes().size(),
        "Recipe list should be empty when no recipes are added");
  }

  @Test
  @DisplayName("Test Remove Recipe from Empty List Throws Exception")
  void removeRecipeFromEmptyListThrowsException() {
    assertThrows(IndexOutOfBoundsException.class, () -> recipeManager.removeRecipe(0),
        "Removing a recipe from an empty list should throw an IndexOutOfBoundsException");
  }

  @Test
  @DisplayName("Test Add Multiple Recipes")
  void addMultipleRecipes() {
    Recipe recipe2 = new Recipe("Test Recipe 2", "Description for recipe 2");
    recipeManager.addRecipe(recipe);
    recipeManager.addRecipe(recipe2);

    assertEquals(2, recipeManager.getRecipes().size(),
        "Recipe list should contain 2 recipes after adding two recipes");
    assertEquals(recipe, recipeManager.getRecipes().get(0),
        "First recipe should match the added recipe");
    assertEquals(recipe2, recipeManager.getRecipes().get(1),
        "Second recipe should match the added recipe");
  }

  @Test
  @DisplayName("Test Remove Recipe Updates Index")
  void removeRecipeUpdatesIndex() {
    Recipe recipe2 = new Recipe("Test Recipe 2", "Description for recipe 2");
    recipeManager.addRecipe(recipe);
    recipeManager.addRecipe(recipe2);

    recipeManager.removeRecipe(0);

    assertEquals(1, recipeManager.getRecipes().size(),
        "Recipe list should contain 1 recipe after removing one recipe");
    assertEquals(recipe2, recipeManager.getRecipes().getFirst(),
        "Remaining recipe should match the second added recipe");
  }

  @Test
  @DisplayName("Test Get Name of Recipe Manager")
  void getNameOfRecipeManager() {
    assertEquals("Cook Book", recipeManager.getName(),
        "RecipeManager name should match the provided name");
  }

  @Test
  @DisplayName("Test TableData Serialization")
  void tableDataSerialization() {
    recipeManager.addRecipe(recipe);

    TableData tableData = recipeManager.toTableData();

    assertNotNull(tableData, "TableData should not be null");
    assertEquals(1, tableData.data().size(), "TableData should have 1 row");
    assertEquals("Test Recipe", tableData.data().getFirst().get(1),
        "First row should contain the name of the added recipe");
  }

}