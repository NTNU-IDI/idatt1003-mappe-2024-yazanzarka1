package org.ntnu.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ntnu.units.Liter;
import org.ntnu.units.Unit;

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
		assertEquals("Milk", grocery.groceryName, "Grocery name should be Milk");
		assertInstanceOf(Liter.class, grocery.unit, "Unit should be an instance of Liter");
		assertEquals(50.0f, grocery.pricePerUnit, "Price per unit should be 50.0");
	}

	@Test
	@DisplayName("Test Set Price Per Unit")
	void testSetPricePerUnit() {
		grocery.pricePerUnit = 60.0f;
		assertEquals(60.0f, grocery.pricePerUnit, "Price per unit should be updated to 60.0");
	}
}