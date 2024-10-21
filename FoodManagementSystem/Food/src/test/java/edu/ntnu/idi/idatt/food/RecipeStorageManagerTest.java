package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryInStorage;
import edu.ntnu.idi.idatt.units.Kilogram;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeStorageManagerTest {

  RecipeStorageManager recipeStorageManager;
  Recipe recipe;
  StorageUnit storageUnit;
  Grocery grocery = new Grocery("Test Grocery", new Kilogram(), 1.0f);
  Grocery grocery1 = new Grocery("Test Grocery2", new Kilogram(), 2.0f);
  Grocery grocery2 = new Grocery("Test Grocery3", new Kilogram(), 3.0f);
  Grocery grocery3 = new Grocery("Test Grocery4", new Kilogram(), 3.0f);


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

    recipeStorageManager = new RecipeStorageManager(recipe, storageUnit);
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
            recipeStorageManager.getStorageDifference(recipe.getGroceries().get(0)),
            "Storage difference should be 0.0f"),
        () -> assertEquals(1.0f,
            recipeStorageManager.getStorageDifference(recipe.getGroceries().get(1)),
            "Storage difference should be 1.0f"),
        () -> assertEquals(2.0f,
            recipeStorageManager.getStorageDifference(recipe.getGroceries().get(2)),
            "Storage difference should be 2.0f"),
        () -> assertEquals(-2.0f,
            recipeStorageManager.getStorageDifference(recipe.getGroceries().get(3)),
            "Storage difference should be -2.0f")
    );
  }

  @Test
  void cookRecipeFailsWhenGroceryNeeded() {
    assertThrows(MissingGroceryInStorage.class, () -> recipeStorageManager.cookRecipe(),
        "Should throw MissingGroceryInStorage");
  }

  @Test
  void cookRecipe() {
    storageUnit.addGrocery(grocery3, 3.0f, new Date());
    recipeStorageManager.cookRecipe();

    StorageEntry storageGrocery = storageUnit.getGrocery(grocery.groceryName);
    StorageEntry storageGrocery1 = storageUnit.getGrocery(grocery1.groceryName);
    StorageEntry storageGrocery2 = storageUnit.getGrocery(grocery2.groceryName);
    StorageEntry storageGrocery3 = storageUnit.getGrocery(grocery3.groceryName);

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
}