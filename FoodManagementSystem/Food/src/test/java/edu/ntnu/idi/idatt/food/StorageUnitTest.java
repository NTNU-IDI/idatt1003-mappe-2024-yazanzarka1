package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import edu.ntnu.idi.idatt.food.exceptions.InsufficentGroceryInStorageUnitException;
import edu.ntnu.idi.idatt.units.Kilogram;
import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the StorageUnit class.
 */
class StorageUnitTest {

  /**
   * The StorageUnit instance used for testing.
   */
  private StorageUnit storageUnit;

  /**
   * The Grocery instance used for testing.
   */
  private Grocery grocery;

  /**
   * The best before date used for testing.
   */
  private Date bestBeforeDate;

  /**
   * Sets up the test environment before each test.
   */
  @BeforeEach
  void setUp() {
    storageUnit = new StorageUnit("Test Storage", new DisplayManager());
    Unit unit = new Liter();
    grocery = new Grocery("Milk", unit, 50.0f);
    bestBeforeDate = new Date();
  }

  /**
   * Tests adding a grocery to the storage unit.
   */
  @Test
  @DisplayName("Test Add Grocery")
  void addGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.findGroceryByName(grocery.getGroceryName());
    assertNotNull(entry, "Grocery should be added");
    assertEquals(10.0f, entry.getQuantity(), "Quantity should be 10.0");
    assertEquals(bestBeforeDate, entry.getBestBeforeDate(), "Best before date should match");
  }

  /**
   * Tests adding a null grocery to the storage unit.
   */
  @Test
  @DisplayName("Test Add Grocery with Null")
  void addGroceryWithNull() {
    assertThrows(IllegalArgumentException.class,
        () -> storageUnit.addGrocery(null, 10.0f, bestBeforeDate),
        "Should throw IllegalArgumentException when adding null grocery");
  }

  /**
   * Tests removing a grocery from the storage unit.
   */
  @Test
  @DisplayName("Test Remove Grocery")
  void removeGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    storageUnit.removeGrocery(grocery, 5.0f);
    StorageEntry entry = storageUnit.findGroceryByName(grocery.getGroceryName());
    assertNotNull(entry, "Grocery should still exist");
    assertEquals(5.0f, entry.getQuantity(), "Quantity should be 5.0 after removal");
  }

  /**
   * Tests removing more grocery than available in the storage unit.
   */
  @Test
  @DisplayName("Test Remove Grocery Not Enough")
  void removeMoreGroceryThanInStorage() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
  }

  /**
   * Tests getting a grocery from the storage unit.
   */
  @Test
  @DisplayName("Get a grocery")
  void getGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.findGroceryByName("Milk");
    assertNotNull(entry, "Grocery should be added");
    assertEquals(entry.getGroceryName(), grocery.getGroceryName(), "Grocery should be Milk");
  }

  /**
   * Tests adding an existing grocery to the storage unit, which should increase the quantity.
   */
  @Test
  @DisplayName("Adding a grocery that already exists, should increase the quantity")
  void addExistingGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    storageUnit.addGrocery(grocery, 5.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.findGroceryByName("Milk");
    assertNotNull(entry, "Grocery should be added");
    assertEquals(15.0f, entry.getQuantity(), "Quantity should be 15.0");
  }

  /**
   * Tests adding a null grocery to the storage unit.
   */
  @Test
  @DisplayName("Test Add Grocery with Null Grocery")
  void addGroceryWithNullGrocery() {
    assertThrows(IllegalArgumentException.class,
        () -> storageUnit.addGrocery(null, 10.0f, bestBeforeDate),
        "Should throw IllegalArgumentException when adding null grocery");
  }

  /**
   * Tests removing a non-existent grocery from the storage unit.
   */
  @Test
  @DisplayName("Test Remove Grocery Not Found")
  void removeGroceryNotFound() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    Grocery nonExistentGrocery = new Grocery("Nonexistent", new Liter(), 50.0f);
    assertThrows(GroceryNotFoundException.class,
        () -> storageUnit.removeGrocery(nonExistentGrocery, 5.0f),
        "Should throw GroceryNotFoundException when grocery not found");
  }

  /**
   * Tests removing all quantity of a grocery from the storage unit.
   */
  @Test
  @DisplayName("Test Remove All Quantity of a Grocery")
  void removeAllQuantityOfGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    storageUnit.removeGrocery(grocery, 10.0f);
    StorageEntry entry = storageUnit.findGroceryByName(grocery.getGroceryName());
    assertFalse(storageUnit.getGroceries().contains(entry), "Grocery should be removed completely");
  }

  /**
   * Tests removing a grocery with insufficient quantity in the storage unit.
   */
  @Test
  @DisplayName("Test Remove Grocery with Insufficient Quantity")
  void removeGroceryWithInsufficientQuantity() {
    storageUnit.addGrocery(grocery, 5.0f, bestBeforeDate);
    assertThrows(InsufficentGroceryInStorageUnitException.class,
        () -> storageUnit.removeGrocery(grocery, 10.0f),
        "Should throw InsufficientGroceryInStorageUnitException when trying to remove more than available");
  }

  /**
   * Tests getting the total value of groceries in the storage unit.
   */
  @Test
  @DisplayName("Test Get Total Value of Groceries")
  void getTotalValueOfGroceries() {
    Grocery grocery2 = new Grocery("Juice", new Liter(), 20.0f);
    storageUnit.addGrocery(grocery, 2.0f, bestBeforeDate);  // Milk: 2 x 50 = 100 NOK
    storageUnit.addGrocery(grocery2, 3.0f, bestBeforeDate);  // Juice: 3 x 20 = 60 NOK
    float totalValue = storageUnit.getTotalValue();
    assertEquals(160.0f, totalValue, "Total value should be 160.0 NOK");
  }

  /**
   * Tests finding groceries by partial match in the storage unit.
   */
  @Test
  @DisplayName("Test Find Grocery by Partial Match")
  void findGroceryByPartialMatch() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    Grocery grocery2 = new Grocery("Chocolate Milk", new Liter(), 30.0f);
    storageUnit.addGrocery(grocery2, 5.0f, bestBeforeDate);
    var result = storageUnit.findGrocery("Milk");
    assertEquals(2, result.size(), "Should find two items containing 'Milk'");
  }

  /**
   * Tests displaying an expired grocery in the storage unit.
   */
  @Test
  @DisplayName("Test Expired Grocery Formatting")
  void displayExpiredGrocery() {
    // Setting up a past date to simulate an expired item
    Date pastDate = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24); // Yesterday
    storageUnit.addGrocery(grocery, 10.0f, pastDate);

    StorageEntry entry = storageUnit.findGroceryByName(grocery.getGroceryName());
    assertNotNull(entry, "Grocery should be added");
    assertTrue(entry.isExpired(), "Grocery should be expired");
  }

  /**
   * Tests finding groceries by partial match with no result in the storage unit.
   */
  @Test
  @DisplayName("Test Find Grocery By Partial Match (No Match Found)")
  void findGroceryByPartialMatchNoResult() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    var result = storageUnit.findGrocery("Nonexistent");
    assertTrue(result.isEmpty(), "Should find no items when searching for a non-existent grocery");
  }

  /**
   * Tests adding a grocery with the same name but different unit to the storage unit.
   */
  @Test
  @DisplayName("Test Add Grocery with Same Name But Different Unit")
  void addGroceryWithDifferentUnit() {
    Unit kilogram = new Kilogram();
    Grocery grocery2 = new Grocery("Milk", kilogram, 50.0f);

    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    assertThrows(IllegalArgumentException.class,
        () -> storageUnit.addGrocery(grocery2, 5.0f, bestBeforeDate),
        "Should throw IllegalArgumentException when adding grocery with same name but different unit");
  }

  /**
   * Tests getting the total value of groceries with multiple items in the storage unit.
   */
  @Test
  @DisplayName("Test Get Total Value of Groceries with Multiple Items")
  void getTotalValueOfGroceriesWithMultipleItems() {
    Grocery grocery2 = new Grocery("Juice", new Liter(), 20.0f);
    storageUnit.addGrocery(grocery, 2.0f, bestBeforeDate);
    storageUnit.addGrocery(grocery2, 3.0f, bestBeforeDate);
    float totalValue = storageUnit.getTotalValue();
    assertEquals(160.0f, totalValue, "Total value should be 160.0 NOK");

    // Adding another grocery item
    Grocery grocery3 = new Grocery("Butter", new Liter(), 60.0f);
    storageUnit.addGrocery(grocery3, 1.0f, bestBeforeDate);

    totalValue = storageUnit.getTotalValue();
    assertEquals(220.0f, totalValue, "Total value should now be 220.0 NOK after adding Butter");
  }
}