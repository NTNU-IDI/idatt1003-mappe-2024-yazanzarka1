package org.ntnu.application.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.food.StorageEntry;
import org.ntnu.food.StorageUnit;

public class DisplayExpiredGroceriesCommand implements Command {

  StorageUnit storageUnit;
  DisplayManager displayManager;
  public DisplayExpiredGroceriesCommand(StorageUnit storageUnit){
    displayManager = new DisplayManager();
    this.storageUnit = storageUnit;
  }
  /**
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    HashMap<String, StorageEntry> storageEntries = storageUnit.getGroceries();
    List<StorageEntry> expiredGroceries = new ArrayList<>();
    float totalExpiredGroceriesValue = 0;
    for (StorageEntry storageEntry : storageEntries.values()){
      if (storageEntry.isExpired()){
        totalExpiredGroceriesValue += storageEntry.getQuantity() + storageEntry.getPricePerUnit();
        expiredGroceries.add(storageEntry);
      }
    }

    storageUnit.displayGroceries(expiredGroceries);
    displayManager.showColoredMessage(String.format("Total value of expired products: %.2f NOK", totalExpiredGroceriesValue), Color.RED);
    displayManager.showSpace();
    return false;

  }

  /**
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Display expired Groceries in your storage unit";
  }
}
