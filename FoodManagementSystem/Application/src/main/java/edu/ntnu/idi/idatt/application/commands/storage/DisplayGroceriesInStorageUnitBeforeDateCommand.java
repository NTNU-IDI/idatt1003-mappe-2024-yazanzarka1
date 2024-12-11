package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.TableData;
import edu.ntnu.idi.idatt.console.validators.DateValidator;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.food.exceptions.GroceryNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.fusesource.jansi.Ansi;

/**
 * Command to display all groceries in a storage unit before a given best before date.
 *
 * @see Command
 * @see StorageUnit
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
    displayManager.showMessage("Display groceries expiring before a specific best-before-date");
    displayManager.showColoredMessage(
        String.format("type '%s' to cancel the operation", InputHandler.CANCEL_WORD),
        Ansi.Color.YELLOW);

    // Get best before date from user
    Date date =
        inputHandler.getDate("Enter best before date (dd.mm.yyyy): ",
            new DateValidator("Invalid date format"));

    // Get all groceries before date and sort them by best before date
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

    // Display all groceries before date
    if (storageEntryBeforeDate.isEmpty()) {
      throw new GroceryNotFoundException(String.format("No groceries expiring before %s",
          new SimpleDateFormat("dd.MM.yyyy").format(date)));
    }

    TableData tableData = storageUnit.toTableData(storageEntryBeforeDate);
    displayManager.printTable(
        String.format("Expiring before %s", new SimpleDateFormat("dd.MM.yyyy").format(date)),
        tableData);

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
