package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.fusesource.jansi.Ansi.Color;

/**
 * Display expired groceries with total value summary.
 */
public class DisplayExpiredGroceriesCommand implements Command {

  StorageUnit storageUnit;
  DisplayManager displayManager;

  /**
   * DisplayExpiredGroceriesCommand constructor.
   *
   * @param storageUnit Storage unit that we should look for expired groceries in.
   */
  public DisplayExpiredGroceriesCommand(StorageUnit storageUnit) {
    displayManager = new DisplayManager();
    this.storageUnit = storageUnit;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    Set<StorageEntry> storageEntries = storageUnit.getGroceries();
    List<StorageEntry> expiredGroceries = new ArrayList<>();
    float totalExpiredGroceriesValue = 0;
    for (StorageEntry storageEntry : storageEntries) {
      if (Boolean.TRUE.equals(storageEntry.isExpired())) {
        totalExpiredGroceriesValue += storageEntry.getQuantity() + storageEntry.getPricePerUnit();
        expiredGroceries.add(storageEntry);
      }
    }

    storageUnit.displayGroceries(expiredGroceries);
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
    return "Display expired Groceries in your storage unit";
  }
}
