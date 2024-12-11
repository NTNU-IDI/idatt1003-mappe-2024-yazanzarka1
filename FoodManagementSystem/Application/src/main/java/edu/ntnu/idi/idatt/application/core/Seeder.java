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
import java.util.List;

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
    // Add groceries with prices and units to grocery manager
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
    List<Grocery> availableGroceries = groceryManager.getAvailableGroceries().stream().toList();
    storageUnit.addGrocery(availableGroceries.get(0), 2.0f,
        addDays(-1)); // Milk, 7 days
    storageUnit.addGrocery(availableGroceries.get(1), 1.0f,
        addDays(-1)); // Avocado, 5 days
    storageUnit.addGrocery(availableGroceries.get(2), 6.0f,
        addDays(21)); // Egg, 21 days
    storageUnit.addGrocery(availableGroceries.get(3), 1.0f,
        addDays(-5)); // Lime, 14 days
    storageUnit.addGrocery(availableGroceries.get(4), 1.0f,
        addDays(-1)); // Paprika, 10 days
    storageUnit.addGrocery(availableGroceries.get(5), 1.0f,
        addDays(7)); // Tomato, 7 days
    storageUnit.addGrocery(availableGroceries.get(6), 1.0f,
        addDays(30)); // Garlic, 30 days
    storageUnit.addGrocery(availableGroceries.get(7), 1.0f,
        addDays(-4)); // Cabbage, 20 days
    storageUnit.addGrocery(availableGroceries.get(8), 1.0f,
        addDays(60)); // Potato, 60 days
    storageUnit.addGrocery(availableGroceries.get(9), 1.0f,
        addDays(-5)); // Carrot, 30 days
    storageUnit.addGrocery(availableGroceries.get(10), 1.0f,
        addDays(3)); // Chicken Filet, 3 days
    storageUnit.addGrocery(availableGroceries.get(11), 1.0f,
        addDays(5)); // Chicken Whole, 5 days
    storageUnit.addGrocery(availableGroceries.get(12), 1.0f,
        addDays(10)); // Beef, 10 days
    storageUnit.addGrocery(availableGroceries.get(13), 1.0f,
        addDays(7)); // Pork, 7 days
    storageUnit.addGrocery(availableGroceries.get(14), 1.0f,
        addDays(5)); // Fish, 5 days
    storageUnit.addGrocery(availableGroceries.get(15), 1.0f,
        addDays(2)); // Bread, 2 days
    storageUnit.addGrocery(availableGroceries.get(16), 1.0f,
        addDays(20)); // Butter, 20 days
    storageUnit.addGrocery(availableGroceries.get(17), 1.0f,
        addDays(30)); // Cheese, 30 days
    storageUnit.addGrocery(availableGroceries.get(18), 1.0f,
        addDays(10)); // Yogurt, 10 days
    storageUnit.addGrocery(availableGroceries.get(19), 1.0f,
        addDays(15)); // Apple, 15 days
    storageUnit.addGrocery(availableGroceries.get(20), 1.0f,
        addDays(10)); // Banana, 10 days
    storageUnit.addGrocery(availableGroceries.get(21), 1.0f,
        addDays(20)); // Orange, 20 days
    storageUnit.addGrocery(availableGroceries.get(22), 1.0f,
        addDays(5)); // Strawberry, 5 days
    storageUnit.addGrocery(availableGroceries.get(23), 1.0f,
        addDays(7)); // Blueberry, 7 days
    storageUnit.addGrocery(availableGroceries.get(24), 1.0f,
        addDays(10)); // Grapes, 10 days

    // Add recipes with steps and groceries to recipe manager
    Recipe recipe1 = new Recipe(
        "Avocado Toast",
        "Toast with avocado slices",
        """
            1. Toast the bread.
            2. Slice the avocado.
            3. Spread the avocado on the toasted bread.
            4. Serve immediately.""",
        4
    );
    recipe1.addGrocery(availableGroceries.get(15), 4.0f); // Bread
    recipe1.addGrocery(availableGroceries.get(1), 1.0f);  // Avocado
    recipeManager.addRecipe(recipe1);

    Recipe recipe2 = new Recipe(
        "Chicken Stir-Fry",
        "A quick stir-fry with chicken and vegetables",
        """
            1. Cut chicken and vegetables into small pieces.
            2. Heat a pan with oil and stir-fry garlic.
            3. Add chicken and cook until browned.
            4. Add vegetables and stir-fry for another 5-7 minutes.
            5. Serve hot.""",
        4
    );
    recipe2.addGrocery(availableGroceries.get(10), 1.0f); // Chicken Filet
    recipe2.addGrocery(availableGroceries.get(9), 0.5f);  // Carrot
    recipe2.addGrocery(availableGroceries.get(4), 0.5f);  // Paprika
    recipe2.addGrocery(availableGroceries.get(6), 0.1f);  // Garlic
    recipeManager.addRecipe(recipe2);

    Recipe recipe3 = new Recipe(
        "Garlic Mashed Potatoes",
        "Creamy mashed potatoes flavored with garlic and butter",
        """
            1. Peel and boil the potatoes until tender.
            2. Mash the potatoes and mix with butter and minced garlic.
            3. Add salt and pepper to taste.
            4. Serve warm.""",
        6
    );
    recipe3.addGrocery(availableGroceries.get(8), 1.5f);  // Potato
    recipe3.addGrocery(availableGroceries.get(6), 0.1f);  // Garlic
    recipe3.addGrocery(availableGroceries.get(16), 0.2f); // Butter
    recipeManager.addRecipe(recipe3);

    Recipe recipe4 = new Recipe(
        "Beef and Cabbage Stew",
        "A hearty stew with tender beef and cabbage",
        """
            1. Cut beef, cabbage, and carrots into small pieces.
            2. Heat oil in a pot and sauté garlic until fragrant.
            3. Add beef and cook until browned.
            4. Add cabbage, carrots, and water to cover.
            5. Simmer for 30-40 minutes.
            6. Serve hot.""",
        6
    );
    recipe4.addGrocery(availableGroceries.get(12), 1.0f); // Beef
    recipe4.addGrocery(availableGroceries.get(7), 0.5f);  // Cabbage
    recipe4.addGrocery(availableGroceries.get(9), 0.3f);  // Carrot
    recipe4.addGrocery(availableGroceries.get(6), 0.1f);  // Garlic
    recipeManager.addRecipe(recipe4);

    Recipe recipe5 = new Recipe(
        "Fruit Salad",
        "A refreshing mix of fruits, perfect for a light dessert or snack",
        """
            1. Wash and chop all fruits into bite-sized pieces.
            2. Mix them in a bowl.
            3. Optionally, drizzle with honey or lemon juice.
            4. Serve chilled.""",
        4
    );
    recipe5.addGrocery(availableGroceries.get(19), 0.5f); // Apple
    recipe5.addGrocery(availableGroceries.get(20), 0.5f); // Banana
    recipe5.addGrocery(availableGroceries.get(21), 0.5f); // Orange
    recipe5.addGrocery(availableGroceries.get(23), 0.3f); // Blueberry
    recipe5.addGrocery(availableGroceries.get(22), 0.3f); // Strawberry
    recipeManager.addRecipe(recipe5);

    Recipe recipe6 = new Recipe(
        "Baked Fish with Lime",
        "Lime-marinated fish baked to perfection",
        """
            1. Marinate the fish with lime juice, garlic, salt, and pepper.
            2. Preheat oven to 180°C.
            3. Bake the fish for 20-25 minutes.
            4. Serve with a garnish of lime slices.""",
        4
    );
    recipe6.addGrocery(availableGroceries.get(14), 1.0f); // Fish
    recipe6.addGrocery(availableGroceries.get(3), 0.2f);  // Lime
    recipe6.addGrocery(availableGroceries.get(6), 0.1f);  // Garlic
    recipeManager.addRecipe(recipe6);

    Recipe recipe7 = new Recipe(
        "Chicken Caesar Salad",
        "A classic Caesar salad with grilled chicken and a hint of garlic",
        """
            1. Grill the chicken and slice into strips.
            2. Chop the cabbage into small pieces.
            3. Mix the cabbage with garlic, avocado, and grilled chicken.
            4. Add dressing and toss to coat.
            5. Serve chilled.""",
        4
    );
    recipe7.addGrocery(availableGroceries.get(10), 0.5f); // Chicken Filet
    recipe7.addGrocery(availableGroceries.get(6), 0.05f); // Garlic
    recipe7.addGrocery(availableGroceries.get(7), 0.2f);  // Cabbage
    recipe7.addGrocery(availableGroceries.get(1), 0.2f);  // Avocado
    recipeManager.addRecipe(recipe7);

    Recipe recipe8 = new Recipe(
        "Tomato and Cheese Sandwich",
        "A simple sandwich with fresh tomato and melted cheese",
        """
            1. Slice the tomato and cheese.
            2. Spread butter on the bread.
            3. Layer the tomato and cheese between the bread slices.
            4. Toast the sandwich until the cheese melts.
            5. Serve warm.""",
        4
    );
    recipe8.addGrocery(availableGroceries.get(15), 4.0f); // Bread
    recipe8.addGrocery(availableGroceries.get(5), 0.4f);  // Tomato
    recipe8.addGrocery(availableGroceries.get(17), 0.3f); // Cheese
    recipe8.addGrocery(availableGroceries.get(16), 0.1f); // Butter
    recipeManager.addRecipe(recipe8);

    Recipe recipe9 = new Recipe(
        "Potato and Garlic Soup",
        "A creamy soup with potatoes and a touch of garlic",
        """
            1. Peel and chop the potatoes.
            2. Boil the potatoes until tender.
            3. Blend the potatoes with garlic and butter until smooth.
            4. Add salt, pepper, and water to achieve desired consistency.
            5. Serve hot.""",
        4
    );
    recipe9.addGrocery(availableGroceries.get(8), 1.0f);  // Potato
    recipe9.addGrocery(availableGroceries.get(6), 0.1f);  // Garlic
    recipe9.addGrocery(availableGroceries.get(16), 0.2f); // Butter
    recipeManager.addRecipe(recipe9);

    Recipe recipe10 = new Recipe(
        "Avocado and Lime Smoothie",
        "A healthy and refreshing smoothie with avocado and lime",
        """
            1. Peel the avocado and remove the seed.
            2. Blend avocado, lime juice, and yogurt until smooth.
            3. Add water or ice cubes if needed.
            4. Serve chilled.""",
        2
    );
    recipe10.addGrocery(availableGroceries.get(1), 0.5f);  // Avocado
    recipe10.addGrocery(availableGroceries.get(3), 0.2f);  // Lime
    recipe10.addGrocery(availableGroceries.get(18), 0.5f); // Yogurt
    recipeManager.addRecipe(recipe10);
  }
}
