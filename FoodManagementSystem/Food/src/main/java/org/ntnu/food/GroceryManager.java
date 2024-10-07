package org.ntnu.food;

import java.util.ArrayList;
import java.util.List;
import org.ntnu.console.DisplayManager;

/**
 *
 */
/*
 * Class for managing groceries
 * 
 */
public class GroceryManager {
	final private List<Grocery> availableGroceries;
	final private DisplayManager displayManager;
	public GroceryManager() {
		availableGroceries = new ArrayList<>();
		displayManager = new DisplayManager();
	}

	public void addGrocery(Grocery grocery) {
		availableGroceries.add(grocery);

	}

	/**
	 * @return List<Grocery> Available Groceries in the application.
	 */
	public List<Grocery> getAvailableGroceries() {
		return availableGroceries;
	}


	/**
	 * @param grocery Grocery to be removed
	 */
	public void removeGrocery(Grocery grocery) {
		availableGroceries.remove(grocery);

	}


	/**
	 * Displays groceries in a table
	 */
	public void displayGroceries() {
		List<String> headers = List.of("Index", "Grocery", "Unit", "NOK / Unit");
		List<List<String>> groceryList = new ArrayList<>();
		for (int i = 0; i < availableGroceries.size(); i++) {
			Grocery entry = availableGroceries.get(i);
			groceryList.add(List.of(String.valueOf(i) ,entry.groceryName, entry.unit.getUnitName(), String.valueOf(entry.pricePerUnit)));
		}
		displayManager.showSpace();
		displayManager.printTable(headers, groceryList);
		displayManager.showSpace();

	}

}