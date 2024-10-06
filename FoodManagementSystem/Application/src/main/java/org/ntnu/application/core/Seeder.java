package org.ntnu.application.core;

import org.ntnu.food.Grocery;
import org.ntnu.food.GroceryManager;
import org.ntnu.food.StorageUnit;
import org.ntnu.units.Kilogram;
import org.ntnu.units.Liter;
import org.ntnu.units.Piece;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Seeder {

	private GroceryManager groceryManager;
	private StorageUnit storageUnit;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	Seeder(GroceryManager groceryManager, StorageUnit storageUnit) {
		this.groceryManager = groceryManager;
		this.storageUnit = storageUnit;
	}

	private Date addDays(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public void Seed() {
		groceryManager.addGrocery(new Grocery("Milk", new Liter(), 24.80f));
		groceryManager.addGrocery(new Grocery("Avocado", new Kilogram(), 94.75f));
		groceryManager.addGrocery(new Grocery("Egg", new Piece(), 4.35f));
		groceryManager.addGrocery(new Grocery("Lime", new Kilogram(), 67.90f));
		groceryManager.addGrocery(new Grocery("Paprika", new Kilogram(), 24.80f));
		groceryManager.addGrocery(new Grocery("Tomato", new Kilogram(), 34.93f));
		groceryManager.addGrocery(new Grocery("Garlic", new Kilogram(), 169.00f));
		groceryManager.addGrocery(new Grocery("Cabbage", new Kilogram(), 23.80f));
		groceryManager.addGrocery(new Grocery("Potato", new Kilogram(), 19.90f));
		groceryManager.addGrocery(new Grocery("Carrot", new Kilogram(), 46.53f));
		groceryManager.addGrocery(new Grocery("Chicken Filet", new Kilogram(), 222.50f));
		groceryManager.addGrocery(new Grocery("Chicken Whole", new Kilogram(), 129.00f));

		// Add groceries with reasonable expiry dates
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().getFirst(), 2.0f, addDays(-1)); // Milk, 7 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(1), 1.0f, addDays(5)); // Avocado, 5 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(2), 6.0f, addDays(21)); // Egg, 21 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(3), 1.0f, addDays(14)); // Lime, 14 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(4), 1.0f, addDays(10)); // Paprika, 10 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(5), 1.0f, addDays(7)); // Tomato, 7 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(6), 1.0f, addDays(30)); // Garlic, 30 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(7), 1.0f, addDays(20)); // Cabbage, 20 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(8), 1.0f, addDays(60)); // Potato, 60 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(9), 1.0f, addDays(30)); // Carrot, 30 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(10), 1.0f, addDays(3)); // Chicken Filet, 3 days
		storageUnit.addGrocery(groceryManager.getAvailableGroceries().get(11), 1.0f, addDays(5)); // Chicken Whole, 5 days
	}
}
