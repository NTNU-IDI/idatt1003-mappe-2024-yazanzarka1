package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.TableRepresentable;
import edu.ntnu.idi.idatt.food.exceptions.GroceryAlreadyExistsException;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Manages Grocery in the app. GroceryManager can add and remove groceries. GroceryManager can also
 * return available groceries. GroceryManager is used by the application to manage groceries.
 *
 * @author yazanzarka
 * @see Grocery
 * @since 0.0.1
 */
public class GroceryManager implements TableRepresentable {

  private final Set<Grocery> availableGroceries;

  /**
   * Initiate GroceryManager.
   */
  public GroceryManager() {
    availableGroceries = new HashSet<>();
  }

  /**
   * Adds Grocery to GroceryManager.
   *
   * @param grocery {@link Grocery} to be added
   * @throws IllegalArgumentException      if grocery is null
   * @throws GroceryAlreadyExistsException if grocery already exists
   * @see Grocery
   */
  public void addGrocery(Grocery grocery) {
    // Check if grocery is null
    if (grocery == null) {
      throw new IllegalArgumentException("Grocery cannot be null");
    }

    // Check if grocery already exists and throw exception if it does
    if (availableGroceries.contains(grocery)) {
      throw new GroceryAlreadyExistsException(
          "Grocery already exists: " + grocery.getGroceryName());
    }

    // Add grocery to available groceries
    availableGroceries.add(grocery);
  }

  /**
   * Get available groceries.
   *
   * @return {@link List} List Available Groceries in the application.
   */
  public List<Grocery> getAvailableGroceries() {
    return availableGroceries.stream().toList();
  }


  /**
   * Removes Grocery from GroceryManager. Throws IndexOutOfBoundsException if index is out of
   * bounds.
   *
   * @param grocery {@link Grocery} to be removed
   * @throws IllegalArgumentException if grocery is null
   * @throws GroceryNotFoundException if grocery is not found
   * @see Grocery
   */
  public void removeGrocery(Grocery grocery) {

    // Check if grocery is null
    if (grocery == null) {
      throw new IllegalArgumentException("Grocery cannot be null");
    }

    // Check if grocery exists in available groceries
    if (!availableGroceries.contains(grocery)) {
      throw new GroceryNotFoundException("Grocery not found: " + grocery.getGroceryName());
    }
    //  Remove grocery from available groceries
    availableGroceries.remove(grocery);
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
        .sorted()
        .map(grocery -> List.of(
            grocery.getGroceryName(), grocery.getUnit().getUnitName(),
            String.format("%.2f NOK", grocery.getPricePerUnit())))
        .forEach(groceryList::add);

    return new TableData(headers, groceryList);

  }

}