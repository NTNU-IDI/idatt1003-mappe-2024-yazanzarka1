package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.exceptions.GroceryAlreadyExistsException;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroceryManagerTest {

  private GroceryManager groceryManager;
  private Grocery grocery;

  @BeforeEach
  void setUp() {
    groceryManager = new GroceryManager();
    Unit unit = new Liter();
    grocery = new Grocery("Milk", unit, 50.0f);
  }

  @Test
  @DisplayName("Test Add Grocery")
  void addGrocery() {
    groceryManager.addGrocery(grocery);
    List<Grocery> groceries = groceryManager.getAvailableGroceries();
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
    List<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertNotNull(groceries, "Grocery list should not be null");
    assertEquals(1, groceries.size(), "Grocery list should contain 1 item");
  }

  @Test
  @DisplayName("Test Remove Grocery")
  void removeGrocery() {
    groceryManager.addGrocery(grocery);
    groceryManager.removeGrocery(grocery);
    List<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(0, groceries.size(), "Grocery list should be empty after removal");
  }

  @Test
  @DisplayName("Test Remove Non-Existent Grocery")
  void removeNonExistentGrocery() {
    groceryManager.addGrocery(grocery);
    assertThrows(GroceryNotFoundException.class,
        () -> groceryManager.removeGrocery(new Grocery("Bread", new Liter(), 20.0f)));
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
    List<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(0, groceries.size(), "Grocery list should be empty initially");
  }

  @Test
  @DisplayName("Test Add Null Grocery Throws Exception")
  void addNullGroceryThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> groceryManager.addGrocery(null),
        "Adding a null grocery should throw an exception");
  }

  @Test
  @DisplayName("Test Remove Null Grocery Throws Exception")
  void removeNullGroceryThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> groceryManager.removeGrocery(null),
        "Removing a null grocery should throw an exception");
  }

  @Test
  @DisplayName("Test Add Grocery with Invalid Price")
  void addGroceryWithInvalidPrice() {
    assertThrows(IllegalArgumentException.class, () -> new Grocery("Sugar", new Liter(), -10.0f));
    assertThrows(IllegalArgumentException.class,
        () -> groceryManager.addGrocery(new Grocery("Sugar", new Liter(), -10.0f)),
        "Adding a grocery with a negative price should throw an exception");
  }


  @Test
  @DisplayName("Test Add Multiple Groceries")
  void addMultipleGroceries() {
    Grocery bread = new Grocery("Bread", new Liter(), 30.0f);
    groceryManager.addGrocery(grocery);
    groceryManager.addGrocery(bread);

    List<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(2, groceries.size(), "Grocery list should contain 2 items");
    assertTrue(groceries.stream().anyMatch(g -> g.getGroceryName().equals("Milk")),
        "Grocery list should contain Milk");
    assertTrue(groceries.stream().anyMatch(g -> g.getGroceryName().equals("Bread")),
        "Grocery list should contain Bread");
  }

  @Test
  @DisplayName("Test Grocery List after Removing All Items")
  void groceryListAfterRemovingAllItems() {
    Grocery bread = new Grocery("Bread", new Liter(), 30.0f);
    groceryManager.addGrocery(grocery);
    groceryManager.addGrocery(bread);

    groceryManager.removeGrocery(grocery);
    groceryManager.removeGrocery(bread);

    List<Grocery> groceries = groceryManager.getAvailableGroceries();
    assertEquals(0, groceries.size(), "Grocery list should be empty after removing all items");
  }


  @Test
  @DisplayName("Test Get Total Groceries Count")
  void getTotalGroceriesCount() {
    Grocery bread = new Grocery("Bread", new Liter(), 30.0f);
    groceryManager.addGrocery(grocery);
    groceryManager.addGrocery(bread);

    int totalGroceries = groceryManager.getAvailableGroceries().size();
    assertEquals(2, totalGroceries, "Total groceries count should be 2");
  }

  @Test
  @DisplayName("Test Removing Grocery Updates Total Count")
  void removingGroceryUpdatesTotalCount() {
    groceryManager.addGrocery(grocery);
    assertEquals(1, groceryManager.getAvailableGroceries().size(),
        "Total groceries count should be 1 after adding");

    groceryManager.removeGrocery(grocery);
    assertEquals(0, groceryManager.getAvailableGroceries().size(),
        "Total groceries count should be 0 after removal");
  }

  @Test
  @DisplayName("Test Grocery Manager Has Correct Headers And Data In Table Data")
  void groceryManagerHasCorrectHeadersInTableData() {
    TableData tableData = groceryManager.toTableData();
    assertEquals(3, tableData.headers().size(), "Table should have 3 header");
    assertEquals("Grocery", tableData.headers().getFirst(), "Header should be 'Grocery Name'");
    groceryManager.addGrocery(grocery);
    tableData = groceryManager.toTableData();
    assertEquals(1, tableData.data().size());
  }


}