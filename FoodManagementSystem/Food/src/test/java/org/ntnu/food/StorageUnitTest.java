package org.ntnu.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ntnu.units.Liter;
import org.ntnu.units.Unit;

class StorageUnitTest {

  private StorageUnit storageUnit;
  private Grocery grocery;
  private Date bestBeforeDate;

  @BeforeEach
  void setUp() {
    storageUnit = new StorageUnit("Test Storage");
    Unit unit = new Liter();
    grocery = new Grocery("Milk", unit, 50.0f);
    bestBeforeDate = new Date();
  }

  @Test
  @DisplayName("Test Add Grocery")
  void addGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    StorageEntry entry = storageUnit.groceries.get(grocery.groceryName);
    assertNotNull(entry, "Grocery should be added");
    assertEquals(10.0f, entry.getQuantity(), "Quantity should be 10.0");
    assertEquals(bestBeforeDate, entry.getBestBeforeDate(), "Best before date should match");
  }

  @Test
  @DisplayName("Test Remove Grocery")
  void removeGrocery() {
    storageUnit.addGrocery(grocery, 10.0f, bestBeforeDate);
    storageUnit.removeGrocery(grocery, 5.0f);
    StorageEntry entry = storageUnit.groceries.get(grocery.groceryName);
    assertNotNull(entry, "Grocery should still exist");
    assertEquals(5.0f, entry.getQuantity(), "Quantity should be 5.0 after removal");
  }

}