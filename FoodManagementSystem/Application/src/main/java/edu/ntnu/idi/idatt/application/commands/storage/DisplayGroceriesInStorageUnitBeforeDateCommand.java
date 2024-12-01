package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.DateValidator;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Command to display all groceries in a storage unit before a given best before date.
 */
public class DisplayGroceriesInStorageUnitBeforeDateCommand implements Command {


  StorageUnit storageUnit;
  DisplayManager displayManager;
  InputHandler inputHandler;

  /**
   * Initiate the command with a storage unit.
   *
   * @param storageUnit    StorageUnit to display groceries from.
   * @param displayManager DisplayManager to display messages.
   * @param inputHandler   InputHandler to get input from user.
   */
  public DisplayGroceriesInStorageUnitBeforeDateCommand(StorageUnit storageUnit,
      DisplayManager displayManager,
      InputHandler inputHandler) {
    this.storageUnit = storageUnit;
    this.displayManager = displayManager;
    this.inputHandler = inputHandler;
  }

  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  @Override
  public Boolean execute() {
    Date date =
        inputHandler.getDate("Enter best before date (dd.mm.yyyy): ",
            new DateValidator("Invalid date format"));
    List<StorageEntry> storageEntryBeforeDate = storageUnit.getGroceries().stream()
        .filter(storageEntry -> storageEntry.getBestBeforeDate().before(date))
        .sorted((entry1, entry2) -> {
          if (entry1.getBestBeforeDate().before(entry2.getBestBeforeDate())) {
            return -1;
          } else if (entry1.getBestBeforeDate().after(entry2.getBestBeforeDate())) {
            return 1;
          } else {
            return 0;
          }
        }).toList();

    if (storageEntryBeforeDate.isEmpty()) {
      displayManager.showMessage(
          "No groceries expiring before " + new SimpleDateFormat("dd.MM.yyyy").format(date));
      return false;
    }

    TableData tableData = storageUnit.toTableData(storageEntryBeforeDate);
    displayManager.printTable(tableData);

    displayManager.showMessage(
        "Displayed groceries before " + new SimpleDateFormat("dd.MM.yyyy").format(date));

    return false;
  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Display groceries expiring before a specific best-before-date";
  }
}
