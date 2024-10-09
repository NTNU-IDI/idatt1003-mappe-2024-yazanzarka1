package org.ntnu.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ntnu.units.Liter;
import org.ntnu.units.Unit;

class StorageEntryTest {

  private StorageEntry storageEntry;
  private Date bestBeforeDate;

  @BeforeEach
  void setUp() {
    Unit unit = new Liter();
    Grocery grocery = new Grocery("Milk", unit, 50.0f);
    bestBeforeDate = new Date();
    storageEntry = new StorageEntry(grocery, 10.0f, bestBeforeDate);
  }

  @Test
  @DisplayName("Test StorageEntry Initialization")
  void testStorageEntryInitialization() {
    assertEquals("Milk", storageEntry.groceryName, "Grocery name should be Milk");
    assertInstanceOf(Liter.class, storageEntry.unit, "Unit should be an instance of Liter");
    assertEquals(50.0f, storageEntry.pricePerUnit, "Price per unit should be 50.0");
    assertEquals(10.0f, storageEntry.getQuantity(), "Quantity should be 10.0");
    assertEquals(bestBeforeDate, storageEntry.getBestBeforeDate(), "Best before date should match");
  }

  @Test
  @DisplayName("Test Set Quantity")
  void testSetQuantity() {
    storageEntry.setQuantity(20.0f);
    assertEquals(20.0f, storageEntry.getQuantity(), "Quantity should be updated to 20.0");
  }

  @Test
  @DisplayName("Test Set Best Before Date")
  void testSetBestBeforeDate() {
    Date newDate = new Date();
    storageEntry.setBestBeforeDate(newDate);
    assertEquals(newDate, storageEntry.getBestBeforeDate(), "Best before date should be updated");
  }

  @Test
  @DisplayName("Test Add Quantity")
  void testAddQuantity() {
    storageEntry.addQuantity(5.0f);
    assertEquals(15.0f, storageEntry.getQuantity(), "Quantity should be increased to 15.0");
  }

  @Test
  @DisplayName("Test Subtract Quantity")
  void testSubtractQuantity() {
    storageEntry.subtractQuantity(3.0f);
    assertEquals(7.0f, storageEntry.getQuantity(), "Quantity should be decreased to 7.0");
  }
}