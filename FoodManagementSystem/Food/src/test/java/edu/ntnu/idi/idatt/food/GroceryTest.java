package edu.ntnu.idi.idatt.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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


}