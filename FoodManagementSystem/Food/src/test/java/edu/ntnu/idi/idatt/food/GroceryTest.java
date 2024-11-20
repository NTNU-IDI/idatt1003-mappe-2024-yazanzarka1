package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.units.Liter;
import edu.ntnu.idi.idatt.units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GroceryTest {

  private Grocery grocery;

  @BeforeEach
  void setUp() {
    Unit unit = new Liter();
    grocery = new Grocery("Milk", unit, 50.0f);
  }

  @Test
  @DisplayName("Test Grocery Initialization")
  void testGroceryInitialization() {
    assertEquals("Milk", grocery.getGroceryName(), "Grocery name should be Milk");
    assertInstanceOf(Liter.class, grocery.getUnit(), "Unit should be an instance of Liter");
    assertEquals(50.0f, grocery.getPricePerUnit(), "Price per unit should be 50.0");
  }

  @Test
  void setPricePerUnit() {
    grocery.setPricePerUnit(60.0f);
    assertEquals(60.0f, grocery.getPricePerUnit(), "Price per unit should be 60.0");
  }

  @Test
  @DisplayName("Test Grocery with Null Name")
  void testGroceryNullName() {
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Grocery(null, new Liter(), 50.0f),
        "Expected exception for null name"
    );
  }

  @Test
  @DisplayName("Test Grocery with Blank Name")
  void testGroceryBlankName() {
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Grocery("  ", new Liter(), 50.0f),
        "Expected exception for blank name"
    );
  }

  @Test
  @DisplayName("Test Grocery with Null Unit")
  void testGroceryNullUnit() {
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Grocery("Milk", null, 50.0f),
        "Expected exception for null unit"
    );
    assertTrue(exception.getMessage().contains("Unit cannot be null"));
  }

  @Test
  @DisplayName("Test Grocery with Negative Price")
  void testGroceryNegativePrice() {
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new Grocery("Milk", new Liter(), -10.0f),
        "Expected exception for negative price"
    );
  }

  @Test
  @DisplayName("Test Grocery with Zero Price")
  void testGroceryZeroPrice() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Grocery("Milk", new Liter(), 0.0f),
        "Expected exception for zero price"
    );

  }

  @Test
  @DisplayName("Test Equals and HashCode for Identical Groceries")
  void testEqualsAndHashCodeIdentical() {
    Unit unit = new Liter();
    Grocery identicalGrocery = new Grocery("Milk", unit, 50.0f);
    assertEquals(grocery, identicalGrocery, "Groceries with the same name should be equal");
    assertEquals(grocery.hashCode(), identicalGrocery.hashCode(), "Hash codes should match");
  }

  @Test
  @DisplayName("Test Equals and HashCode for Different Groceries")
  void testEqualsAndHashCodeDifferent() {
    Unit unit = new Liter();
    Grocery differentGrocery = new Grocery("Juice", unit, 60.0f);
    assertNotEquals(grocery, differentGrocery, "Groceries with different names should not be equal");
    assertNotEquals(grocery.hashCode(), differentGrocery.hashCode(), "Hash codes should not match");
  }

  @Test
  @DisplayName("Test Set Price Per Unit with Negative Value")
  void testSetPricePerUnitNegative() {
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> grocery.setPricePerUnit(-20.0f),
        "Expected exception for setting a negative price per unit"
    );
  }

  @Test
  @DisplayName("Test Set Price Per Unit with Zero")
  void testSetPricePerUnitZero() {
    assertThrows(
        IllegalArgumentException.class,
        () -> grocery.setPricePerUnit(0.0f),
        "Expected exception for setting a price per unit of zero"
    );
  }
}

