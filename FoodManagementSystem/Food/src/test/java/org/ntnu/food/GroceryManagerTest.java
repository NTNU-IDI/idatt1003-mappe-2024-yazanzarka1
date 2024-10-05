package org.ntnu.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ntnu.units.Liter;
import org.ntnu.units.Unit;

import java.util.List;

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
		assertEquals("Milk", groceries.getFirst().groceryName, "Grocery name should be Milk");
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

}