package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Class for storage units Groceries are stored in storage units with quantity and best before
 * date.
 */
public class StorageUnit {

  public final String name;
  final HashMap<String, StorageEntry> groceries;
  final DisplayManager displayManager;

  /**
   * Initiates a storage unit with a name.
   *
   * @param name Name of the storage unit
   */
  public StorageUnit(String name) {
    this.name = name;
    displayManager = new DisplayManager();
    groceries = new HashMap<>();
  }

  /**
   * Adds a grocery to the storage unit. If the grocery already exists in the storage unit, the
   * quantity will be updated. If the grocery does not exist in the storage unit, a new entry will
   * be created.
   */
  public void addGrocery(Grocery grocery, float quantity, Date bestBeforeDate) {
    if (groceries.containsKey(grocery.groceryName)) {
      displayManager.showColoredMessage(
          String.format("%s - Already exists, updating quantity", grocery.groceryName),
          Color.BLUE);
      StorageEntry newGrocery = groceries.get(grocery.groceryName);
      newGrocery.addQuantity(quantity);
      newGrocery.setBestBeforeDate(bestBeforeDate);
      groceries.put(grocery.groceryName, newGrocery);
      return;
    }

    groceries.put(grocery.groceryName, new StorageEntry(grocery, quantity, bestBeforeDate));
  }

  /**
   * Removes a grocery from the storage unit. If the quantity is less than the quantity in the
   * storage unit, the quantity will be updated.
   */
  public void removeGrocery(Grocery grocery, float quantity) {
    if (!groceries.containsKey(grocery.groceryName)) {
      return;
    }

    StorageEntry groceryToRemove = groceries.get(grocery.groceryName);
    if (groceryToRemove.quantity < quantity) {
      displayManager.showColoredMessage("Error: Cannot remove more than you have", Color.RED);
      return;
    }
    if (groceryToRemove.quantity == quantity) {
      groceries.remove(grocery.groceryName);
      return;
    }
    groceryToRemove.subtractQuantity(quantity);
    groceries.put(grocery.groceryName, groceryToRemove);


  }

  public Map<String, StorageEntry> getGroceries() {
    return groceries;
  }

  /**
   * Displays a list of all storage entries in the storage unit. Displays grocery name, unit, price
   * per unit, quantity, best before date and value.
   */
  public void displayGroceries() {
    List<String> headers = List.of("Grocery", "Unit", "NOK / Unit", "Quantity", "B.B.D", "Value");
    List<List<String>> groceryList = new ArrayList<>();
    for (Map.Entry<String, StorageEntry> entry : groceries.entrySet()) {
      StorageEntry storageEntry = entry.getValue();
      groceryList.add(
          List.of(storageEntry.groceryName, storageEntry.unit.getUnitName(), String.valueOf(
                  storageEntry.pricePerUnit), String.valueOf(storageEntry.quantity),
              formatBestBeforeDate(storageEntry),
              String.format("%.2f NOK", storageEntry.quantity * storageEntry.getPricePerUnit())));
    }
    displayManager.showSpace();
    displayManager.printTable(headers, groceryList);
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
    List<List<String>> groceryList = new ArrayList<>();
    for (StorageEntry storageEntry : storageEntries) {
      groceryList.add(
          List.of(storageEntry.groceryName, storageEntry.unit.getUnitName(), String.valueOf(
                  storageEntry.pricePerUnit), String.valueOf(storageEntry.quantity),
              formatBestBeforeDate(storageEntry),
              String.format("%.2f NOK", storageEntry.quantity * storageEntry.getPricePerUnit())));
    }
    displayManager.showSpace();
    displayManager.printTable(headers, groceryList);
    displayManager.showSpace();
  }

  /**
   * Finds a grocery in the storage unit. The search is case-insensitive and can be a partial
   * match.
   *
   * @param query Query to search for
   * @return List of storage entries that match the query
   */
  public List<StorageEntry> findGrocery(String query) {
    return groceries.entrySet().stream().filter(entry -> entry.getKey().toLowerCase()
        .contains(query.toLowerCase())).map(Map.Entry::getValue).toList();
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

}