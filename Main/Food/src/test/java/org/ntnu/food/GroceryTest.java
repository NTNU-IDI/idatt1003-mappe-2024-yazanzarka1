package org.ntnu.food;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ntnu.units.Liter;

class GroceryTest {

	private Grocery grocery;

	@BeforeEach
	void setUp() {
		grocery = new Grocery("Milk", new Liter(50));
	}

	@Test
	@DisplayName("Adding to grocery")
	void add_to_grocery() {
		grocery.add(new Liter(50));
		float amount = grocery.groceryAmount.getValue();
		Assertions.assertEquals(100, amount);
	}

	@Test
	@DisplayName("Removing from grocery")
	void remove_from_grocery() {
		grocery.remove(new Liter(50));
		float amount = grocery.groceryAmount.getValue();
		Assertions.assertEquals(0, amount);
	}
}