package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StorageUnitTest {

  private StorageUnit storageUnit;
  private Grocery grocery;
  private Date bestBeforeDate;

  @BeforeEach
  void setUp() {
    storageUnit = new StorageUnit("Test Storage", new DisplayManager());
    Unit unit = new Liter();
    grocery = new Grocery("Milk", unit, 50.0f);
    bestBeforeDate = new Date();
  }

  @Test
  @DisplayName("Test Add Grocery")
  void addGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.findGroceryByName(grocery.getGroceryName());
    assertNotNull(entry, "Grocery should be added");
    assertEquals(10.0f, entry.getQuantity(), "Quantity should be 10.0");
    assertEquals(bestBeforeDate, entry.getBestBeforeDate(), "Best before date should match");
  }

  @Test
  @DisplayName("Test Remove Grocery")
  void removeGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    storageUnit.removeGrocery(grocery, 5.0f);
    StorageEntry entry = storageUnit.findGroceryByName(grocery.getGroceryName());
    assertNotNull(entry, "Grocery should still exist");
    assertEquals(5.0f, entry.getQuantity(), "Quantity should be 5.0 after removal");
  }

  @Test
  @DisplayName("Test Remove Grocery Not Enough")
  void removeMoreGroceryThanInStorage() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
  }

  @Test
  @DisplayName("Get a grocery")
  void getGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.findGroceryByName("Milk");
    assertNotNull(entry, "Grocery should be added");
    assertEquals(entry.getGroceryName(), grocery.getGroceryName(), "Grocery should be Milk");
  }

  @Test
  @DisplayName("Adding a grocery that already exists, should increase the quantity")
  void addExistingGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    storageUnit.addGrocery(grocery, 5.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.findGroceryByName("Milk");
    assertNotNull(entry, "Grocery should be added");
    assertEquals(15.0f, entry.getQuantity(), "Quantity should be 15.0");
  }

  @Test
  @DisplayName("Test Add Grocery with Null Grocery")
  void addGroceryWithNullGrocery() {
    assertThrows(IllegalArgumentException.class, () -> storageUnit.addGrocery(null, 10.0f, bestBeforeDate),
        "Should throw IllegalArgumentException when adding null grocery");
  }

  @Test
  @DisplayName("Test Remove Grocery Not Found")
  void removeGroceryNotFound() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    Grocery nonExistentGrocery = new Grocery("Nonexistent", new Liter(), 50.0f);
    assertThrows(
        GroceryNotFoundException.class, () -> storageUnit.removeGrocery(nonExistentGrocery, 5.0f),
        "Should throw GroceryNotFoundException when grocery not found");
  }


}