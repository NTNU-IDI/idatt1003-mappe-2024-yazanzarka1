package edu.ntnu.idi.idatt.application.commands.storage;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.StorageEntry;
import edu.ntnu.idi.idatt.food.StorageUnit;
import java.text.ParseException;
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
   * @param storageUnit StorageUnit to display groceries from.
   * @param displayManager DisplayManager to display messages.
   * @param inputHandler InputHandler to get input from user.
   */
  public DisplayGroceriesInStorageUnitBeforeDateCommand(StorageUnit storageUnit, DisplayManager displayManager,
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
    Date date;
    try {
      date = new SimpleDateFormat("dd.MM.yyyy").parse(
          inputHandler.getInput("Enter best before date (dd.mm.yyyy): "));
    } catch (ParseException e) {
      throw new UserInputException("Invalid date format: " + e.getMessage());
    }
    List<StorageEntry> storageEntryBeforeDate = storageUnit.getGroceries().stream()
        .filter(storageEntry -> storageEntry.getBestBeforeDate().before(date)).sorted().toList();

    if (storageEntryBeforeDate.isEmpty()) {
      displayManager.showMessage("No groceries found before " + date);
      return false;
    }


    storageUnit.displayGroceries(storageEntryBeforeDate);
    displayManager.showMessage("Displayed groceries before " + date);

    return false;
  }

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  @Override
  public String getDescription() {
    return "Display all groceries in a storage unit before a given best before date";
  }
}
