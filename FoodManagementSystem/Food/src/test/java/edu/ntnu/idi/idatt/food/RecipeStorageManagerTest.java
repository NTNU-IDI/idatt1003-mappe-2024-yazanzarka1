package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryForRecipeException;
import edu.ntnu.idi.idatt.food.exceptions.MissingGroceryInStorage;
import edu.ntnu.idi.idatt.units.Kilogram;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    assertThrows(MissingGroceryForRecipeException.class, () -> recipeStorageManager.cookRecipe(),
        "Should throw MissingGroceryInStorage");

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


}