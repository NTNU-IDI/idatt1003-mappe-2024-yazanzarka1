package edu.ntnu.idi.idatt.application.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.application.commands.storage.AddGroceryToStorageUnitCommand;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.TestInputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.StorageUnit;
import edu.ntnu.idi.idatt.units.Liter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddGroceryToStorageUnitCommandTest {


  AddGroceryToStorageUnitCommand addGroceryToStorageUnitCommand;
  GroceryManager groceryManager;
  StorageUnit storageUnit;


  @BeforeEach
  void setUp() {
    this.groceryManager = new GroceryManager();
    this.storageUnit = new StorageUnit("TestStorageUnit");
  }

  @Test
  void executeWithValidParameteresTest() {
    this.groceryManager.addGrocery(new Grocery("Milk", new Liter(), 200.0f));
    this.addGroceryToStorageUnitCommand =
        new AddGroceryToStorageUnitCommand(groceryManager, storageUnit,
            new DisplayManager(),
            new TestInputHandler(new String[] {"Milk", "1", "04.05.2021"}));

    addGroceryToStorageUnitCommand.execute();

    assertEquals(1, storageUnit.getGroceries().size());
  }

  @Test
  void executeWithInvalidParametersTest() {
    this.groceryManager.addGrocery(new Grocery("Milk", new Liter(), 200));
    this.addGroceryToStorageUnitCommand =
        new AddGroceryToStorageUnitCommand(groceryManager, storageUnit,
            new DisplayManager(),
            new TestInputHandler(new String[] {"0", "1", "invalidDate"}));

    assertThrows(UserInputException.class, () -> addGroceryToStorageUnitCommand.execute());
  }
}
