package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.fusesource.jansi.Ansi.Color;

/**
 * Display expired groceries with total value summary.
 *
 * @see Command
 * @see StorageUnit
 */
public class DisplayExpiredGroceriesCommand implements Command {

  StorageUnit storageUnit;
  DisplayManager displayManager;

  /**
   * DisplayExpiredGroceriesCommand constructor.
   *
   * @param storageUnit Storage unit that we should look for expired groceries in.
   * @param displayManager DisplayManager to display the expired groceries.
   */
  public DisplayExpiredGroceriesCommand(StorageUnit storageUnit, DisplayManager displayManager) {
    this.displayManager = displayManager;
    this.storageUnit = storageUnit;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    // Get all groceries from storage unit
    List<StorageEntry> storageEntries = storageUnit.getGroceries();
    List<StorageEntry> expiredGroceries = new ArrayList<>();
    float totalExpiredGroceriesValue = 0;
    for (StorageEntry storageEntry : storageEntries) {
      // Check if grocery is expired
      if (Boolean.TRUE.equals(storageEntry.isExpired())) {
        // Add to total value of expired groceries
        totalExpiredGroceriesValue += storageEntry.getQuantity() + storageEntry.getPricePerUnit();
        // Add to list of expired groceries
        expiredGroceries.add(storageEntry);
      }
    }

    // Sort expired groceries by best before date
    expiredGroceries = expiredGroceries.stream().sorted(
        Comparator.comparing(StorageEntry::getBestBeforeDate)).toList();

    // Display expired groceries
    displayManager.printTable("Expired Groceries", storageUnit.toTableData(expiredGroceries));
    displayManager.showColoredMessage(
        String.format("Total value of expired products: %.2f NOK", totalExpiredGroceriesValue),
        Color.RED);
    displayManager.showSpace();
    return false;

  }

  /**
   * .
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return String.format("Display expired groceries in %s", storageUnit.getName().toLowerCase());
  }
}
