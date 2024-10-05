package org.ntnu.food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.ntnu.console.DisplayManager;

/*
 * Class for managing groceries
 * 
 */
public class GroceryManager {
	final private List<Grocery> availableGroceries;
	final private DisplayManager displayManager;
	public GroceryManager() {
		availableGroceries = new ArrayList<Grocery>();
		displayManager = new DisplayManager();
	}

	public void addGrocery(Grocery grocery) {
		availableGroceries.add(grocery);

	}

	public List<Grocery> getAvailableGroceries() {
		return availableGroceries;
	}

	public void removeGrocery(Grocery grocery) {
		availableGroceries.remove(grocery);

	}

	/*
	 * Displays a list of all groceries in the grocery manager.
	 * 
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