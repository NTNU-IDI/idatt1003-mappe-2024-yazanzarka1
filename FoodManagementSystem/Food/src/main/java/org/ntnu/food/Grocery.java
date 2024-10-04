package org.ntnu.food;
import org.ntnu.units.Unit;

import java.util.Date;

public class Grocery {

	final String groceryName;
	final Unit unit;
	public float pricePerUnit;

	public Grocery(String itemName, Unit unit,float pricePerUnit) {
		this.groceryName = itemName;
		this.unit = unit;
		this.pricePerUnit = pricePerUnit;
	}

}
