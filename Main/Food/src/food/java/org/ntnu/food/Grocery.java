package org.ntnu.food;

import org.ntnu.units.Unit;

public class Grocery {

	final Unit groceryAmount;

	public Grocery(String itemName, Unit itemAmount) {
		this.groceryAmount = itemAmount;
	}

	public Grocery add(Unit amount) {
		this.groceryAmount.plus(amount);
		return this;
	}

	public Grocery remove(Unit amount) {
		this.groceryAmount.subtract(amount);
		return this;
	}
}
