package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.TableRepresentable;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import edu.ntnu.idi.idatt.food.exceptions.InsufficentGroceryInStorageUnitException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Class for storage units. Groceries are stored in storage units with quantity and best before
 * date. StorageUnit can add, remove and display groceries. StorageUnit can also find groceries and
 * get the total value of all groceries in the storage unit. StorageUnit is used by the application
 * to manage groceries.
 */
public class StorageUnit implements TableRepresentable {

  public final String name;
  private final Map<String, StorageEntry> groceries;

  /**
   * Initiates a storage unit with a name.
   *
   * @param name Name of the storage unit
   */
  public StorageUnit(String name) {
    this.name = name;
    groceries = new HashMap<>();
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

    String groceryName = grocery.getGroceryName();
    StorageEntry storageEntry = new StorageEntry(grocery, quantity, bestBeforeDate);

    if (groceries.containsKey(groceryName)) {
      StorageEntry existingEntry = groceries.get(groceryName);
      if (existingEntry.getUnit() != storageEntry.getUnit()) {
        throw new IllegalArgumentException("Unit of grocery does not match existing grocery");
      }
      existingEntry.addQuantity(storageEntry.getQuantity());
      existingEntry.setBestBeforeDate(storageEntry.getBestBeforeDate());
    } else {
      groceries.put(groceryName, storageEntry);
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

    String groceryName = grocery.getGroceryName();
    StorageEntry existingEntry = groceries.get(groceryName);

    if (existingEntry == null) {
      throw new GroceryNotFoundException("Grocery not found in storage unit");
    }

    if (existingEntry.getQuantity() < quantity) {
      throw new InsufficentGroceryInStorageUnitException(
          "Insufficient quantity of " + groceryName + " in storage unit");
    }
    if (existingEntry.getQuantity() == quantity) {
      groceries.remove(groceryName);
      return;
    }
    existingEntry.subtractQuantity(quantity);
  }

  public List<StorageEntry> getGroceries() {
    return groceries.values().stream().toList();
  }

  /**
   * Serialize StorageUnit to a tableData object with headers and rows.
   *
   * @return TableData with headers and rows
   * @see TableData
   */
  public TableData toTableData() {
    List<String> headers = List.of("Grocery", "Unit", "NOK / Unit", "Quantity", "B.B.D", "Value");
    List<List<String>> groceryList = groceries.values().stream()
        .sorted()
        .map(storageEntry -> List.of(
            storageEntry.getGroceryName(),
            storageEntry.getUnit().getUnitName(),
            String.valueOf(storageEntry.getPricePerUnit()),
            String.valueOf(storageEntry.getQuantity()),
            formatBestBeforeDate(storageEntry),
            String.format("%.2f NOK", storageEntry.getQuantity() * storageEntry.getPricePerUnit())))
        .toList();

    return new TableData(headers, groceryList);
  }

  /**
   * Serialize StorageUnit to a tableData object with headers and rows.
   *
   * @param storageEntries List of storage entries to serialize
   * @return TableData with headers and rows
   * @see TableData
   */
  public TableData toTableData(List<StorageEntry> storageEntries) {
    List<String> headers = List.of("Grocery", "Unit", "NOK / Unit", "Quantity", "B.B.D", "Value");
    List<List<String>> storageEntryTableBody = storageEntries.stream()
        .sorted()
        .map(storageEntry -> List.of(
            storageEntry.getGroceryName(),
            storageEntry.getUnit().getUnitName(),
            String.valueOf(storageEntry.getPricePerUnit()),
            String.valueOf(storageEntry.getQuantity()),
            formatBestBeforeDate(storageEntry),
            String.format("%.2f NOK", storageEntry.getQuantity() * storageEntry.getPricePerUnit())))
        .toList();

    return new TableData(headers, storageEntryTableBody);
  }


  /**
   * Get a grocery from the storage unit by name.
   *
   * @param groceryName Name of the grocery
   * @return Storage entry of the grocery
   */
  public StorageEntry findGroceryByName(String groceryName) {
    return groceries.get(groceryName);
  }

  /**
   * Finds a grocery in the storage unit. The search is case-insensitive and can be a partial
   * match.
   *
   * @param query Query to search for
   * @return List of storage entries that match the query
   */
  public List<StorageEntry> findGrocery(String query) {
    return groceries.values().stream()
        .filter(entry -> entry.getGroceryName().toLowerCase().contains(query.toLowerCase()))
        .toList();
  }

  /**
   * Formats best before date.
   *
   * @param storageEntry Storage entry to format best before date
   * @return Formatted best before date
   */
  private String formatBestBeforeDate(StorageEntry storageEntry) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    String formattedDate = formatter.format(storageEntry.getBestBeforeDate());
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
    return groceries.values().stream()
        .map(entry -> entry.getQuantity() * entry.getPricePerUnit())
        .reduce(Float::sum)
        .orElse(0f);
  }
}
