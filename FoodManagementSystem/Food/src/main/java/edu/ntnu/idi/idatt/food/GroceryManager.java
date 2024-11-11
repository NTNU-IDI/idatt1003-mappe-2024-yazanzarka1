package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.ArrayList;
import java.util.List;


/**
 * Manages Grocery in the app.
 * GroceryManager can add, remove and display groceries.
 * GroceryManager can also return available groceries.
 * GroceryManager is used by the application to manage groceries.
 *
 * @see Grocery
 */
public class GroceryManager {

  private final List<Grocery> availableGroceries;
  private final DisplayManager displayManager;

  /**
   * Initiate GroceryManager.
   */
  public GroceryManager() {
    availableGroceries = new ArrayList<>();
    displayManager = new DisplayManager();
  }

  /**
   * Adds Grocery to GroceryManager. Multiple groceries can have the same name.
   *
   * @param grocery grocery to be added
   */
  public void addGrocery(Grocery grocery) {
    availableGroceries.add(grocery);
  }

  /**
   * Get available groceries.
   *
   * @return List Available Groceries in the application.
   */
  public List<Grocery> getAvailableGroceries() {
    return availableGroceries;
  }


  /**
   * Removes Grocery from GroceryManager.
   * Throws IndexOutOfBoundsException if index is out of bounds.
   *
   * @param index index of grocery to be removed
   */
  public void removeGrocery(int index) {
    if (index < 0 || index >= availableGroceries.size()) {
      throw new IndexOutOfBoundsException(
          "Index out of bounds: No grocery found at index " + index);
    }
    availableGroceries.remove(index);
  }


  /**
   * Displays groceries in a table.
   */
  public void displayGroceries() {
    List<String> headers = List.of("Index", "Grocery", "Unit", "NOK / Unit");
    List<List<String>> groceryList = new ArrayList<>();

    availableGroceries.stream()
        .map(grocery -> List.of(String.valueOf(availableGroceries.indexOf(grocery)),
            grocery.groceryName, grocery.unit.getUnitName(), String.valueOf(grocery.pricePerUnit)))
        .forEach(groceryList::add);

    displayManager.showSpace();
    displayManager.printTable(headers, groceryList);
    displayManager.showSpace();

  }

}