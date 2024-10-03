package org.ntnu.food;
import org.ntnu.units.Unit;

public class Grocery {

	final String itemName;
	final Unit quantity;

	public Grocery(String itemName, Unit quantity) {
		this.itemName = itemName;
		this.quantity = quantity;
	}

	public Grocery add(Unit amount) {
		this.quantity.plus(amount);
		return this;
	}

	public Grocery remove(Unit amount) {
		this.quantity.subtract(amount);
		return this;
	}
}
