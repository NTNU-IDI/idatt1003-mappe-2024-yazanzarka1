package edu.ntnu.idi.idatt.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.application.commands.grocery.AddGroceryCommand;
import edu.ntnu.idi.idatt.console.TestInputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.GroceryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddGroceryCommandTest {

  GroceryManager groceryManager;
  AddGroceryCommand addGroceryCommand;

  @BeforeEach
  void setUp() {
    this.groceryManager = new GroceryManager();
  }

  @Test
  void executeWithValidParameters() {
    this.addGroceryCommand = new AddGroceryCommand(groceryManager,
        new TestInputHandler(new String[] {"Milk", "1", "200"}));

    addGroceryCommand.execute();

    assertAll(
        () -> assertEquals(1, groceryManager.getAvailableGroceries().size(),
            "Grocery list should contain 1 item"),
        () -> assertEquals("Milk",
            groceryManager.getAvailableGroceries().getFirst().getGroceryName(),
            "Grocery name should be Milk"),
        () -> assertEquals(200, groceryManager.getAvailableGroceries().getFirst().getPricePerUnit(),
            "Grocery quantity should be 200"),
        () -> assertEquals("KG",
            groceryManager.getAvailableGroceries().getFirst().getUnit().getUnitName(),
            "Grocery unit should be Liter")
    );
  }

  @Test
  void executeWithInvalidParameters() {
    this.addGroceryCommand = new AddGroceryCommand(groceryManager,
        new TestInputHandler(new String[] {"Milk", "notUnit", "200"}));
    assertThrows(UserInputException.class, () -> addGroceryCommand.execute(),
        "Should throw UserInputException if unit is not between 1-3");
  }
}