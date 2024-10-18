package edu.ntnu.idi.idatt.food;
import edu.ntnu.idi.idatt.console.DisplayManager;
import java.util.ArrayList;
import java.util.List;


/**
 * Manages Grocery in the app.
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
   * Adds Grocery to GroceryManager.
   *
   * @param grocery grocery to be added
   */
  public void addGrocery(Grocery grocery) {
    availableGroceries.add(grocery);

  }

  /**
   * .
   *
   * @return List Available Groceries in the application.
   */
  public List<Grocery> getAvailableGroceries() {
    return availableGroceries;
  }


  /**
   * Removes Grocery from GroceryManager.
   *
   * @param grocery Grocery to be removed
   */
  public void removeGrocery(Grocery grocery) {
    availableGroceries.remove(grocery);

  }


  /**
   * Displays groceries in a table.
   */
  public void displayGroceries() {
    List<String> headers = List.of("Index", "Grocery", "Unit", "NOK / Unit");
    List<List<String>> groceryList = new ArrayList<>();
    for (int i = 0; i < availableGroceries.size(); i++) {
      Grocery entry = availableGroceries.get(i);
      groceryList.add(List.of(String.valueOf(i), entry.groceryName, entry.unit.getUnitName(),
          String.valueOf(entry.pricePerUnit)));
    }
    displayManager.showSpace();
    displayManager.printTable(headers, groceryList);
    displayManager.showSpace();

  }

}