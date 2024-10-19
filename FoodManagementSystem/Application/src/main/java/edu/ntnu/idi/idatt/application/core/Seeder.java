package edu.ntnu.idi.idatt.application.core;

import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.Recipe;
import edu.ntnu.idi.idatt.food.RecipeManager;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.units.Kilogram;
import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Piece;
import java.util.Calendar;
import java.util.Date;

/**
 * Seeder class to seed the application with groceries, storage units and recipes.
 */
public class Seeder {

  private final GroceryManager groceryManager;
  private final StorageUnit storageUnit;
  private final RecipeManager recipeManager;

  Seeder(GroceryManager groceryManager, StorageUnit storageUnit, RecipeManager recipeManager) {
    this.groceryManager = groceryManager;
    this.storageUnit = storageUnit;
    this.recipeManager = recipeManager;
  }

  private Date addDays(int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return calendar.getTime();
  }

  /**
   * Seed the application with groceries, storage units and recipes.
   */
  public void seed() {
    // Add groceries
    groceryManager.addGrocery(new Grocery("Milk", new Liter(), 24.80f));
    groceryManager.addGrocery(new Grocery("Avocado", new Kilogram(), 94.75f));
    groceryManager.addGrocery(new Grocery("Egg", new Piece(), 4.35f));
    groceryManager.addGrocery(new Grocery("Lime", new Kilogram(), 67.90f));
    groceryManager.addGrocery(new Grocery("Paprika", new Kilogram(), 24.80f));
    groceryManager.addGrocery(new Grocery("Tomato", new Kilogram(), 34.93f));
    groceryManager.addGrocery(new Grocery("Garlic", new Kilogram(), 169.00f));
    groceryManager.addGrocery(new Grocery("Cabbage", new Kilogram(), 23.80f));
    groceryManager.addGrocery(new Grocery("Potato", new Kilogram(), 19.90f));
    groceryManager.addGrocery(new Grocery("Carrot", new Kilogram(), 46.53f));
    groceryManager.addGrocery(new Grocery("Chicken Filet", new Kilogram(), 222.50f));
    groceryManager.addGrocery(new Grocery("Chicken Whole", new Kilogram(), 129.00f));
    groceryManager.addGrocery(new Grocery("Beef", new Kilogram(), 299.00f));
    groceryManager.addGrocery(new Grocery("Pork", new Kilogram(), 199.00f));
    groceryManager.addGrocery(new Grocery("Fish", new Kilogram(), 150.00f));
    groceryManager.addGrocery(new Grocery("Bread", new Piece(), 20.00f));
    groceryManager.addGrocery(new Grocery("Butter", new Kilogram(), 80.00f));
    groceryManager.addGrocery(new Grocery("Cheese", new Kilogram(), 120.00f));
    groceryManager.addGrocery(new Grocery("Yogurt", new Liter(), 30.00f));
    groceryManager.addGrocery(new Grocery("Apple", new Kilogram(), 40.00f));
    groceryManager.addGrocery(new Grocery("Banana", new Kilogram(), 30.00f));
    groceryManager.addGrocery(new Grocery("Orange", new Kilogram(), 50.00f));
    groceryManager.addGrocery(new Grocery("Strawberry", new Kilogram(), 100.00f));
    groceryManager.addGrocery(new Grocery("Blueberry", new Kilogram(), 120.00f));
    groceryManager.addGrocery(new Grocery("Grapes", new Kilogram(), 90.00f));

    // Add groceries with reasonable expiry dates
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(0), 2.0f,
        addDays(-1)); // Milk, 7 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(1), 1.0f,
        addDays(-1)); // Avocado, 5 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(2), 6.0f,
        addDays(21)); // Egg, 21 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(3), 1.0f,
        addDays(-5)); // Lime, 14 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(4), 1.0f,
        addDays(-1)); // Paprika, 10 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(5), 1.0f,
        addDays(7)); // Tomato, 7 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(6), 1.0f,
        addDays(30)); // Garlic, 30 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(7), 1.0f,
        addDays(-4)); // Cabbage, 20 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(8), 1.0f,
        addDays(60)); // Potato, 60 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(9), 1.0f,
        addDays(-5)); // Carrot, 30 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(10), 1.0f,
        addDays(3)); // Chicken Filet, 3 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(11), 1.0f,
        addDays(5)); // Chicken Whole, 5 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(12), 1.0f,
        addDays(10)); // Beef, 10 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(13), 1.0f,
        addDays(7)); // Pork, 7 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(14), 1.0f,
        addDays(5)); // Fish, 5 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(15), 1.0f,
        addDays(2)); // Bread, 2 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(16), 1.0f,
        addDays(20)); // Butter, 20 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(17), 1.0f,
        addDays(30)); // Cheese, 30 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(18), 1.0f,
        addDays(10)); // Yogurt, 10 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(19), 1.0f,
        addDays(15)); // Apple, 15 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(20), 1.0f,
        addDays(10)); // Banana, 10 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(21), 1.0f,
        addDays(20)); // Orange, 20 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(22), 1.0f,
        addDays(5)); // Strawberry, 5 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(23), 1.0f,
        addDays(7)); // Blueberry, 7 days
    storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(24), 1.0f,
        addDays(10)); // Grapes, 10 days

    // Add recipes
    for (int i = 0; i < 25; i++) {
      Recipe recipe = new Recipe("Recipe " + (i + 1), "Description for recipe " + (i + 1));
      for (int j = 0; j < 5; j++) {
        recipe.addGrocery(groceryManager.getAvailableGroceries().get((i + j) % 25), 1.0f);
      }
      recipeManager.addRecipe(recipe);
    }
  }
}
