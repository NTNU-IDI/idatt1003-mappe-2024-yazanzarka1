package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import edu.ntnu.idi.idatt.food.exceptions.InsufficentGroceryInStorageUnitException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Class for storage units. Groceries are stored in storage units with quantity and best before
 * date. StorageUnit can add, remove and display groceries. StorageUnit can also find groceries and
 * get the total value of all groceries in the storage unit. StorageUnit is used by the application
 * to manage groceries.
 */
public class StorageUnit {

  public final String name;
  private final Set<StorageEntry> groceries;
  final DisplayManager displayManager;

  /**
   * Initiates a storage unit with a name.
   *
   * @param name Name of the storage unit
   */
  public StorageUnit(String name, DisplayManager displayManager) {
    this.name = name;
    this.displayManager = displayManager;
    groceries = new HashSet<>();
  }

  /**
   * Adds a grocery to the storage unit. If the grocery already exists in the storage unit, the
   * quantity will be updated. If the grocery does not exist in the storage unit, a new entry will
   * be created.
   *
   * @param grocery        Grocery to add.
   * @param quantity       Quantity of grocery
   * @param bestBeforeDate Best before date of grocery
   */
  public void addGrocery(Grocery grocery, float quantity, Date bestBeforeDate) {
    if (grocery == null) {
      throw new IllegalArgumentException("Grocery cannot be null");
    }
    StorageEntry storageEntry = new StorageEntry(grocery, quantity, bestBeforeDate);
    if (groceries.contains(storageEntry)) {
      StorageEntry existingEntry = findGroceryByName(storageEntry.getGroceryName());
      existingEntry.addQuantity(storageEntry.getQuantity());
      existingEntry.setBestBeforeDate(storageEntry.getBestBeforeDate());
    } else {
      groceries.add(storageEntry);
    }

  }

  /**
   * Removes a grocery from the storage unit. If the quantity is less than the quantity in the
   * storage unit, the quantity will be updated.
   */
  public void removeGrocery(Grocery grocery, float quantity) {

    if (grocery == null) {
      throw new IllegalArgumentException("Grocery cannot be null");
    }

    StorageEntry existingEntry = findGroceryByName(grocery.getGroceryName());

    if (existingEntry == null) {
      throw new GroceryNotFoundException("Grocery not found in storage unit");
    }

    if (existingEntry.quantity < quantity) {
      throw new InsufficentGroceryInStorageUnitException(
          "Insufficient quantity of " + grocery.getGroceryName() + " in storage unit");
    }
    if (existingEntry.quantity == quantity) {
      groceries.remove(existingEntry);
      return;
    }
    existingEntry.subtractQuantity(quantity);
  }

  public Set<StorageEntry> getGroceries() {
    return groceries;
  }

  /**
   * Displays a list of all storage entries in the storage unit. Displays grocery name, unit, price
   * per unit, quantity, best before date and value.
   */
  public void displayGroceries() {
    List<String> headers = List.of("Grocery", "Unit", "NOK / Unit", "Quantity", "B.B.D", "Value");
    List<List<String>> groceryList = groceries.stream()
        .sorted()
        .map(storageEntry -> List.of(
            storageEntry.getGroceryName(),
            storageEntry.getUnit().getUnitName(),
            String.valueOf(storageEntry.getPricePerUnit()),
            String.valueOf(storageEntry.quantity),
            formatBestBeforeDate(storageEntry),
            String.format("%.2f NOK", storageEntry.quantity * storageEntry.getPricePerUnit())))
        .collect(Collectors.toList());

    displayManager.showSpace();
    displayManager.printTable("Groceries in Fridge", headers, groceryList);
    displayManager.showMessage("Total value: " + getTotalValue() + " NOK");
    displayManager.showSpace();
  }

  /**
   * Displays a list of storage entries in the storage unit. Displays grocery name, unit, price per
   * unit, quantity, best before date and value.
   *
   * @param storageEntries List of storage entries to display
   */
  public void displayGroceries(List<StorageEntry> storageEntries) {
    List<String> headers = List.of("Grocery", "Unit", "NOK / Unit", "Quantity", "B.B.D", "Value");
    List<List<String>> groceryList = storageEntries.stream()
        .map(storageEntry -> List.of(
            storageEntry.getGroceryName(),
            storageEntry.getUnit().getUnitName(),
            String.valueOf(storageEntry.getPricePerUnit()),
            String.valueOf(storageEntry.quantity),
            formatBestBeforeDate(storageEntry),
            String.format("%.2f NOK", storageEntry.quantity * storageEntry.getPricePerUnit())))
        .collect(Collectors.toList());

    displayManager.showSpace();
    displayManager.printTable(headers, groceryList);
    displayManager.showSpace();
  }

  /**
   * Get a grocery from the storage unit by name.
   *
   * @param groceryName Name of the grocery
   * @return Storage entry of the grocery
   */
  public StorageEntry findGroceryByName(String groceryName) {
    return groceries.stream()
        .filter(entry -> entry.getGroceryName().equalsIgnoreCase(groceryName))
        .findFirst()
        .orElse(null);
  }

  /**
   * Finds a grocery in the storage unit. The search is case-insensitive and can be a partial
   * match.
   *
   * @param query Query to search for
   * @return List of storage entries that match the query
   */
  public List<StorageEntry> findGrocery(String query) {
    return groceries.stream()
        .filter(entry -> entry.getGroceryName().toLowerCase().contains(query.toLowerCase()))
        .collect(Collectors.toList());
  }

  /**
   * Formats best before date.
   *
   * @param storageEntry Storage entry to format best before date
   * @return Formatted best before date
   */
  private String formatBestBeforeDate(StorageEntry storageEntry) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    String formattedDate = formatter.format(storageEntry.bestBeforeDate);
    if (storageEntry.isExpired().equals(Boolean.TRUE)) {
      return Ansi.ansi().bg(Color.RED).a(formattedDate).reset().toString();
    }
    return formattedDate;
  }

  /**
   * Get total value of all groceries in the storage unit.
   *
   * @return Total value of all groceries in the storage unit
   */
  public float getTotalValue() {
    return groceries.stream()
        .map(entry -> entry.quantity * entry.getPricePerUnit())
        .reduce(Float::sum)
        .orElse(0f);
  }
}
