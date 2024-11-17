package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.units.Kilogram;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecipeSuggestionProviderTest {

  RecipeSuggestionProvider recipeSuggestionProvider;
  RecipeManager recipeManager;
  StorageUnit storageUnit;
  Recipe recipeWithLongLastingProducts;
  Recipe recipeWithShortLastingProducts;
  Recipe recipeWithExpiredProducts;
  Recipe recipeWithMoreGorceriesThanInStorage;
  Recipe recipeWithNoProducts;



  @BeforeEach
  void setUp() {
    RecipeManager recipeManager = new RecipeManager("Recipe Manager", new DisplayManager());
    StorageUnit storageUnit = new StorageUnit("Storage Unit", new DisplayManager());
    recipeSuggestionProvider = new RecipeSuggestionProvider(recipeManager, storageUnit);

    Grocery potato = new Grocery("Potato", new Kilogram(), 50.0f);
    Grocery milk = new Grocery("Milk", new Kilogram(), 23.0f);
    Grocery fish = new Grocery("Fish", new Kilogram(), 500.0f);
    Grocery salad = new Grocery("Salad", new Kilogram(), 23.0f);
    Grocery garlic = new Grocery("Garlic", new Kilogram(), 20.0f);
    Grocery onion = new Grocery("Onion", new Kilogram(), 20.0f);
    Grocery avocado = new Grocery("Avocado", new Kilogram(), 20.0f);


    storageUnit.addGrocery(potato, 1.0f, addDays(4));
    storageUnit.addGrocery(milk, 1.0f, addDays(3));
    storageUnit.addGrocery(fish, 1.0f, addDays(-2));
    storageUnit.addGrocery(salad, 1.0f, addDays(-1));
    storageUnit.addGrocery(garlic, 1.0f, addDays(25));
    storageUnit.addGrocery(onion, 1.0f, addDays(20));
    
    recipeWithLongLastingProducts = new Recipe("Fish and Chips", "Fish and Chips");
    recipeWithLongLastingProducts.addGrocery(potato, 1.0f);
    recipeWithLongLastingProducts.addGrocery(onion, 1.0f);
    recipeWithLongLastingProducts.addGrocery(garlic, 1.0f);
    recipeWithLongLastingProducts.addGrocery(milk, 1.0f);

    recipeWithShortLastingProducts = new Recipe("Salad", "Salad");
    recipeWithShortLastingProducts.addGrocery(salad, 1.0f);
    recipeWithShortLastingProducts.addGrocery(milk, 1.0f);
    recipeWithShortLastingProducts.addGrocery(potato, 1.0f);

    recipeWithExpiredProducts = new Recipe("Expired products", "Expired products");
    recipeWithExpiredProducts.addGrocery(fish, 1.0f);
    recipeWithExpiredProducts.addGrocery(salad, 1.0f);

    recipeWithMoreGorceriesThanInStorage = new Recipe("More groceries", "More groceries");
    recipeWithMoreGorceriesThanInStorage.addGrocery(potato, 1.0f);
    recipeWithMoreGorceriesThanInStorage.addGrocery(onion, 1.0f);
    recipeWithMoreGorceriesThanInStorage.addGrocery(avocado, 1.0f);

    recipeWithNoProducts = new Recipe("No products", "No products");
    recipeManager.addRecipe(recipeWithLongLastingProducts);
    recipeManager.addRecipe(recipeWithShortLastingProducts);
    recipeManager.addRecipe(recipeWithNoProducts);
    recipeManager.addRecipe(recipeWithExpiredProducts);
    recipeManager.addRecipe(recipeWithMoreGorceriesThanInStorage);





  }

  @Test
  @DisplayName("Test suggestRecipe, should return 3 recipes with 5 total recipes")
  void suggestRecipe() {
    List<SuggestedRecipe> suggestedRecipes = recipeSuggestionProvider.suggestRecipe();
    assertEquals(3, suggestedRecipes.size());
  }

  @Test
  @DisplayName("Test suggestRecipe, should sort recipes by score")
  void suggestRecipeShouldBeSorted() {
    List<SuggestedRecipe> suggestedRecipes = recipeSuggestionProvider.suggestRecipe();
    for (int i = 0; i < suggestedRecipes.size() - 1; i++) {
      assertTrue(suggestedRecipes.get(i).score() >= suggestedRecipes.get(i + 1).score());
    }
  }

  @Test
  @DisplayName("Test suggestRecipe, should prioritize recipes with short lasting products")
  void suggestRecipeShouldPrioritizeShortLastingProducts() {
    List<SuggestedRecipe> suggestedRecipes = recipeSuggestionProvider.suggestRecipe();
    assertEquals("Salad", suggestedRecipes.getFirst().recipe().getName());
  }

  private Date addDays(int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, days);
    return calendar.getTime();
  }
}