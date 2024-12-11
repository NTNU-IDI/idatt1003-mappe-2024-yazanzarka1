package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    assertEquals("Milk", storageEntry.getGroceryName(), "Grocery name should be Milk");
    assertInstanceOf(Liter.class, storageEntry.getUnit(), "Unit should be an instance of Liter");
    assertEquals(50.0f, storageEntry.getPricePerUnit(), "Price per unit should be 50.0");
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

  @Test
  @DisplayName("Test isExpired")
  void testIsExpired() {
    Date expiredDate = new Date(System.currentTimeMillis() - 1000);
    storageEntry.setBestBeforeDate(expiredDate);
    assertEquals(true, storageEntry.isExpired(), "Storage entry should be expired");
  }

  @Test
  @DisplayName("Test CompareTo")
  void testCompareTo() {
    StorageEntry storageEntry2 =
        new StorageEntry(new Grocery("Milk", new Liter(), 50.0f), 10.0f, bestBeforeDate);
    assertEquals(0, storageEntry.compareTo(storageEntry2), "Storage entries should be equal");
  }

  @Test
  @DisplayName("Test Add Quantity with Negative Value")
  void testAddQuantityNegative() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      storageEntry.addQuantity(-5.0f);
    });
    assertEquals("Quantity must be between 0.01 and 999.99", exception.getMessage());
  }

  @Test
  @DisplayName("Test Set Quantity with Negative Value")
  void testSetQuantityNegative() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      storageEntry.setQuantity(-5.0f);
    });
    assertEquals("Quantity cannot be less than 0", exception.getMessage());
  }

  @Test
  @DisplayName("Test Subtract Quantity with Negative Value")
  void testSubtractQuantityNegative() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      storageEntry.subtractQuantity(-3.0f);
    });
    assertEquals("Quantity cannot be less than or equal to 0", exception.getMessage());
  }

  @Test
  @DisplayName("Test Equals with Same Object")
  void testEqualsSameObject() {
    assertEquals(storageEntry, storageEntry, "Storage entry should be equal to itself");
  }

  @Test
  @DisplayName("Test Equals with Different Object")
  void testEqualsDifferentObject() {
    StorageEntry anotherStorageEntry = new StorageEntry(
        new Grocery("Milk", new Liter(), 50.0f), 10.0f, bestBeforeDate);
    assertEquals(storageEntry, anotherStorageEntry,
        "Storage entries with the same data should be equal");
  }

  @Test
  @DisplayName("Test Equals with Null")
  void testEqualsNull() {
    assertNotEquals(storageEntry, null, "Storage entry should not be equal to null");
  }

  @Test
  @DisplayName("Test Equals with Different Class")
  void testEqualsDifferentClass() {
    String differentClassObject = "Not a StorageEntry";
    assertNotEquals(storageEntry, differentClassObject,
        "Storage entry should not be equal to a different class object");
  }

  @Test
  @DisplayName("Test HashCode")
  void testHashCode() {
    StorageEntry anotherStorageEntry = new StorageEntry(
        new Grocery("Milk", new Liter(), 50.0f), 10.0f, bestBeforeDate);
    assertEquals(storageEntry.hashCode(), anotherStorageEntry.hashCode(),
        "Hash codes of equal storage entries should be the same");
  }

  @Test
  @DisplayName("Test Subtract Quantity Leading to Zero")
  void testSubtractQuantityToZero() {
    storageEntry.subtractQuantity(10.0f);
    assertEquals(0.0f, storageEntry.getQuantity(), "Quantity should be reduced to 0");
  }

  @Test
  @DisplayName("Test IsExpired with Future Date")
  void testIsExpiredFutureDate() {
    Date futureDate =
        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 1 day in the future
    storageEntry.setBestBeforeDate(futureDate);
    assertFalse(storageEntry.isExpired(), "Storage entry should not be expired with a future date");
  }
}