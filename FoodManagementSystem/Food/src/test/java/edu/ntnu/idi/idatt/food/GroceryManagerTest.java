package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.exceptions.GroceryAlreadyExistsException;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroceryManagerTest {

  private GroceryManager groceryManager;
  private Grocery grocery;

  @BeforeEach
  void setUp() {
    groceryManager = new GroceryManager(new DisplayManager());
    Unit unit = new Liter();
    grocery = new Grocery("Milk", unit, 50.0f);
  }

  @Test
  @DisplayName("Test Add Grocery")
  void addGrocery() {
    groceryManager.addGrocery(grocery);
    Set<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(1, groceries.size(), "Grocery list should contain 1 item");
    assertTrue(groceries.stream().findFirst().isPresent(), "Grocery list should contain 1 item");
    assertEquals("Milk", groceries.stream().findFirst().get().getGroceryName(),
        "Grocery name should be Milk");
  }

  @Test
  @DisplayName("Test Add Grocery Fails When Exists")
  void addGroceryFailsWhenExists() {
    groceryManager.addGrocery(grocery);
    assertThrows(GroceryAlreadyExistsException.class, () -> groceryManager.addGrocery(grocery));
  }

  @Test
  @DisplayName("Test Get Available Groceries")
  void getAvailableGroceries() {
    groceryManager.addGrocery(grocery);
    Set<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertNotNull(groceries, "Grocery list should not be null");
    assertEquals(1, groceries.size(), "Grocery list should contain 1 item");
  }

  @Test
  @DisplayName("Test Remove Grocery")
  void removeGrocery() {
    groceryManager.addGrocery(grocery);
    groceryManager.removeGrocery(grocery.getGroceryName());
    Set<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(0, groceries.size(), "Grocery list should be empty after removal");
  }

  @Test
  @DisplayName("Test Remove Non-Existent Grocery")
  void removeNonExistentGrocery() {
    groceryManager.addGrocery(grocery);
    assertThrows(GroceryNotFoundException.class,
        () -> groceryManager.removeGrocery("Non-existent grocery"));
  }

  @Test
  @DisplayName("Test Add Duplicate Grocery with Different Unit")
  void addDuplicateGroceryWithDifferentUnit() {
    Unit unit2 = new Liter();
    Grocery grocery2 = new Grocery("Milk", unit2, 60.0f); // Same name, different price
    groceryManager.addGrocery(grocery);
    assertThrows(GroceryAlreadyExistsException.class, () -> groceryManager.addGrocery(grocery2));

  }

  @Test
  @DisplayName("Test Empty Grocery List")
  void testEmptyGroceryList() {
    Set<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(0, groceries.size(), "Grocery list should be empty initially");
  }



}