package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.food.exceptions.GroceryAlreadyExistsException;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Manages Grocery in the app. GroceryManager can add, remove and display groceries. GroceryManager
 * can also return available groceries. GroceryManager is used by the application to manage
 * groceries.
 *
 * @see Grocery
 */
public class GroceryManager {

  private final Set<Grocery> availableGroceries;
  private final DisplayManager displayManager;

  /**
   * Initiate GroceryManager.
   */
  public GroceryManager(DisplayManager displayManager) {
    availableGroceries = new HashSet<>();
    this.displayManager = displayManager;
  }

  /**
   * Adds Grocery to GroceryManager. Multiple groceries can have the same name.
   *
   * @param grocery grocery to be added
   */
  public void addGrocery(Grocery grocery) {
    if (availableGroceries.contains(grocery)) {
      throw new GroceryAlreadyExistsException(
          "Grocery already exists: " + grocery.getGroceryName());
    }
    availableGroceries.add(grocery);
  }

  /**
   * Get available groceries.
   *
   * @return List Available Groceries in the application.
   */
  public Set<Grocery> getAvailableGroceries() {
    return availableGroceries;
  }


  /**
   * Removes Grocery from GroceryManager. Throws IndexOutOfBoundsException if index is out of
   * bounds.
   *
   * @param name name of grocery to be removed
   */
  public void removeGrocery(String name) {

    if (!availableGroceries.removeIf(grocery -> grocery.getGroceryName().equals(name))) {
      throw new GroceryNotFoundException("Grocery not found: " + name);
    }
  }


  /**
   * Serialize GroceryManager to a tableData object with headers and rows.
   *
   * @return TableData with headers and rows
   * @see TableData
   */
  public TableData toTableData() {
    // Headers for the table
    List<String> headers = List.of("Grocery", "Unit", "Amount");
    List<List<String>> groceryList = new ArrayList<>();

    availableGroceries.stream()
        .map(grocery -> List.of(
            grocery.getGroceryName(), grocery.getUnit().getUnitName(),
            String.valueOf(grocery.getPricePerUnit())))
        .forEach(groceryList::add);


    return new TableData(headers, groceryList);

  }

}