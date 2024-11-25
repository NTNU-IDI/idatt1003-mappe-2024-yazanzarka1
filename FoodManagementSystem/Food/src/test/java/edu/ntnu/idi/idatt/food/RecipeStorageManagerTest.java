package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryForRecipeException;
import edu.ntnu.idi.idatt.units.Kilogram;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecipeStorageManagerTest {

  RecipeStorageManager recipeStorageManager;
  RecipeStorageManager missingGroceryRecipeStorageManager;
  Recipe recipe;
  Recipe missingGroceryRecipe;
  StorageUnit storageUnit;
  Grocery grocery = new Grocery("Test Grocery", new Kilogram(), 1.0f);
  Grocery grocery1 = new Grocery("Test Grocery2", new Kilogram(), 2.0f);
  Grocery grocery2 = new Grocery("Test Grocery3", new Kilogram(), 3.0f);
  Grocery grocery3 = new Grocery("Test Grocery4", new Kilogram(), 3.0f);
  Grocery grocery4 = new Grocery("Test Grocery5", new Kilogram(), 3.0f);


  @BeforeEach
  void setUp() {
    recipe = new Recipe("Test Recipe", "Test Description");
    storageUnit = new StorageUnit("Test Storage Unit");
    storageUnit.addGrocery(grocery, 1.0f, new Date());
    storageUnit.addGrocery(grocery1, 2.0f, new Date());
    storageUnit.addGrocery(grocery2, 3.0f, new Date());
    storageUnit.addGrocery(grocery3, 1.0f, new Date());

    recipe.addGrocery(grocery, 1.0f);
    recipe.addGrocery(grocery1, 1.0f);
    recipe.addGrocery(grocery2, 1.0f);
    recipe.addGrocery(grocery3, 3.0f);

    missingGroceryRecipe = new Recipe("Missing Grocery Recipe", "Test Description");
    missingGroceryRecipe.addGrocery(grocery, 1.0f);
    missingGroceryRecipe.addGrocery(grocery1, 1.0f);
    missingGroceryRecipe.addGrocery(grocery4, 1.0f);

    recipeStorageManager = new RecipeStorageManager(recipe, storageUnit);

    missingGroceryRecipeStorageManager =
        new RecipeStorageManager(missingGroceryRecipe, storageUnit);

  }

  @Test
  void getRecipe() {
    assertEquals(recipe, recipeStorageManager.getRecipe(), "Recipe should be Test Recipe");
  }

  @Test
  void getStorageUnit() {
    assertEquals(storageUnit, recipeStorageManager.getStorageUnit(),
        "Storage unit should be Test Storage Unit");
  }

  @Test
  void getStorageDifference() {
    assertAll(
        () -> assertEquals(0.0f,
            recipeStorageManager.getStorageDifference(recipe.getGrocery(grocery.getGroceryName())),
            "Storage difference should be 0.0f"),
        () -> assertEquals(1.0f,
            recipeStorageManager.getStorageDifference(recipe.getGrocery(grocery1.getGroceryName())),
            "Storage difference should be 1.0f"),
        () -> assertEquals(2.0f,
            recipeStorageManager.getStorageDifference(recipe.getGrocery(grocery2.getGroceryName())),
            "Storage difference should be 2.0f"),
        () -> assertEquals(-2.0f,
            recipeStorageManager.getStorageDifference(recipe.getGrocery(grocery3.getGroceryName())),
            "Storage difference should be -2.0f"),
        () -> assertEquals(
            -missingGroceryRecipe.getGrocery(grocery4.getGroceryName()).amount(),
            recipeStorageManager.getStorageDifference(missingGroceryRecipe.getGrocery(grocery4.getGroceryName())),
            "Storage difference should be -2.0f")
    );
  }

  @Test
  void cookRecipeFailsWhenGroceryNeeded() {
    MissingGroceryForRecipeException exception = assertThrows(MissingGroceryForRecipeException.class, () -> recipeStorageManager.cookRecipe(),
        "Should throw MissingGroceryForRecipeException");

    assertEquals(1, exception.getGroceries().size());

    assertThrows(MissingGroceryForRecipeException.class,
        () -> missingGroceryRecipeStorageManager.cookRecipe());
  }

  @Test
  void cookRecipe() {
    storageUnit.addGrocery(grocery3, 3.0f, new Date());
    recipeStorageManager.cookRecipe();

    StorageEntry storageGrocery = storageUnit.findGroceryByName(grocery.getGroceryName());
    StorageEntry storageGrocery1 = storageUnit.findGroceryByName(grocery1.getGroceryName());
    StorageEntry storageGrocery2 = storageUnit.findGroceryByName(grocery2.getGroceryName());
    StorageEntry storageGrocery3 = storageUnit.findGroceryByName(grocery3.getGroceryName());

    Assertions.assertAll(
        () -> assertNull(storageGrocery, "Grocery should be 1.0f"),
        () -> assertEquals(1.0f, storageGrocery1.getQuantity(),
            "Grocery should be 1.0f"),
        () -> assertEquals(2.0f, storageGrocery2.getQuantity(),
            "Grocery should be 2.0f"),
        () -> assertEquals(1.0f, storageGrocery3.getQuantity(),
            "Grocery should be 1.0f")
    );
  }

  @Test
  @DisplayName("Test RecipeStorageManager Initialization with Null Recipe")
  void initializeWithNullRecipe() {
    assertThrows(IllegalArgumentException.class, () -> new RecipeStorageManager(null, storageUnit),
        "Initializing with a null recipe should throw an IllegalArgumentException");
  }

  @Test
  @DisplayName("Test RecipeStorageManager Initialization with Null StorageUnit")
  void initializeWithNullStorageUnit() {
    assertThrows(IllegalArgumentException.class, () -> new RecipeStorageManager(recipe, null),
        "Initializing with a null storage unit should throw an IllegalArgumentException");
  }

  @Test
  @DisplayName("Test toTableData Serialization")
  void toTableDataSerialization() {
    TableData tableData = recipeStorageManager.toTableData();

    assertEquals(4, tableData.data().size(), "TableData should contain 4 rows");
    assertEquals(6, tableData.headers().size(), "TableData should have 6 columns");
    assertEquals("Test Grocery", tableData.data().get(0).get(0), "First row should match grocery name");
  }

  @Test
  @DisplayName("Test getStorageDifference with Missing Grocery")
  void getStorageDifferenceWithMissingGrocery() {
    float difference = recipeStorageManager.getStorageDifference(missingGroceryRecipe.getGrocery(grocery4.getGroceryName()));
    assertEquals(-1.0f, difference, "Storage difference for a missing grocery should match its negative amount");
  }

  @Test
  @DisplayName("Test cookRecipe with Partial Storage Availability")
  void cookRecipeWithPartialAvailability() {
    storageUnit.addGrocery(grocery3, 1.0f, new Date()); // Insufficient quantity for grocery3
    assertThrows(MissingGroceryForRecipeException.class, () -> recipeStorageManager.cookRecipe(),
        "Cooking a recipe with insufficient groceries should throw MissingGroceryForRecipeException");
  }

  @Test
  @DisplayName("Test StorageUnit Remains Unchanged on Failed Cook")
  void storageUnitUnchangedOnFailedCook() {
    try {
      recipeStorageManager.cookRecipe();
    } catch (MissingGroceryForRecipeException ignored) {
    }

    assertAll(
        () -> assertEquals(1.0f, storageUnit.findGroceryByName(grocery.getGroceryName()).getQuantity(),
            "Grocery quantity should remain unchanged"),
        () -> assertEquals(2.0f, storageUnit.findGroceryByName(grocery1.getGroceryName()).getQuantity(),
            "Grocery1 quantity should remain unchanged"),
        () -> assertEquals(3.0f, storageUnit.findGroceryByName(grocery2.getGroceryName()).getQuantity(),
            "Grocery2 quantity should remain unchanged"),
        () -> assertEquals(1.0f, storageUnit.findGroceryByName(grocery3.getGroceryName()).getQuantity(),
            "Grocery3 quantity should remain unchanged")
    );
  }

  @Test
  @DisplayName("Test cookRecipe Removes Correct Quantities")
  void cookRecipeRemovesCorrectQuantities() {
    storageUnit.addGrocery(grocery3, 3.0f, new Date());
    recipeStorageManager.cookRecipe();

    assertAll(
        () -> assertNull(storageUnit.findGroceryByName(grocery.getGroceryName()),
            "Grocery should be completely removed from storage"),
        () -> assertEquals(1.0f, storageUnit.findGroceryByName(grocery1.getGroceryName()).getQuantity(),
            "Grocery1 should have correct remaining quantity"),
        () -> assertEquals(2.0f, storageUnit.findGroceryByName(grocery2.getGroceryName()).getQuantity(),
            "Grocery2 should have correct remaining quantity"),
        () -> assertEquals(1.0f, storageUnit.findGroceryByName(grocery3.getGroceryName()).getQuantity(),
            "Grocery3 should have correct remaining quantity")
    );
  }

}