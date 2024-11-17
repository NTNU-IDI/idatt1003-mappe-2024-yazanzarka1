package edu.ntnu.idi.idatt.application;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.application.commands.grocery.AddGroceryCommand;
import edu.ntnu.idi.idatt.console.TestInputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.Grocery;
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

    assertTrue(groceryManager.getAvailableGroceries().stream().findFirst().isPresent(),
        "Grocery list should contain 1 item");

    Grocery
        grocery = groceryManager.getAvailableGroceries().stream().findFirst().get();

    assertAll(
        () -> assertEquals(1, groceryManager.getAvailableGroceries().size(),
            "Grocery list should contain 1 item"),
        () -> assertEquals("Milk",
            grocery.getGroceryName(),
            "Grocery name should be Milk"),
        () -> assertEquals(200, grocery.getPricePerUnit(),
            "Grocery quantity should be 200"),
        () -> assertEquals("KG",
            grocery.getUnit().getUnitName(),
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