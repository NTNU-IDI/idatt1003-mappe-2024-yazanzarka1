package edu.ntnu.idi.idatt.application.commands.grocery;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.console.validators.FloatValidator;
import edu.ntnu.idi.idatt.console.validators.IntegerValidator;
import edu.ntnu.idi.idatt.console.validators.StringValidator;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.units.Unit;
import edu.ntnu.idi.idatt.units.UnitProvider;
import java.util.List;
import java.util.stream.IntStream;
import org.fusesource.jansi.Ansi;

/**
 * Add grocery to GroceryManager.
 */
public class AddGroceryToGroceryManagerCommand implements Command {

  final GroceryManager groceryManager;
  final InputHandler inputHandler;
  final DisplayManager displayManager;
  final UnitProvider unitProvider;

  /**
   * Adds Grocery to GroceryManager.
   *
   * @param groceryManager Grocery manager
   * @param unitProvider   Unit provider
   * @param displayManager Display manager
   * @param inputHandler   Input handler
   */
  public AddGroceryToGroceryManagerCommand(GroceryManager groceryManager, UnitProvider unitProvider,
      DisplayManager displayManager, InputHandler inputHandler) {
    this.groceryManager = groceryManager;
    this.inputHandler = inputHandler;
    this.displayManager = displayManager;
    this.unitProvider = unitProvider;
  }


  @Override
  public Boolean execute() {

    try {
      // Get grocery name from user
      final String groceryName = inputHandler.getString("Enter Grocery Name (Max 25 characters): ",
          new StringValidator("Grocery name must be between 2 and 25 characters", GroceryConstants.MIN_GROCERY_NAME_LENGTH, GroceryConstants.MAX_GROCERY_NAME_LENGTH));

      // Display available units
      List<Unit> units = unitProvider.getUnits();
      System.out.println("Choose a unit:");

      // Display available units for user to choose from
      IntStream.range(0, units.size()).forEach(i -> {
        System.out.println(i + 1 + ". " + units.get(i).getClass().getSimpleName());
      });

      // Get user choice
      int choice = inputHandler.getInt("Enter Choice (1-3): ",
          new IntegerValidator("Choice must be between 1 and 3", 1, units.size()));

      // Check if choice is valid
      if (choice < 1 || choice > units.size()) {
        throw new IndexOutOfBoundsException(
            choice + " is invalid. Must be between 1 and " + units.size());
      }

      // Get the chosen unit
      Unit selectedUnit = units.get(choice - 1);

      // Get price per unit from user
      float groceryPricePerUnit =
          inputHandler.getFloat("Enter price per unit (0.1 - 9999.0): ",
              new FloatValidator("Price must be between 0.1 and 9999.0", GroceryConstants.MIN_PRICE_PER_UNIT, GroceryConstants.MAX_PRICE_PER_UNIT));

      // Add grocery to grocery manager
      groceryManager.addGrocery(new Grocery(groceryName, selectedUnit, groceryPricePerUnit));
      displayManager.showColoredMessage("Grocery added successfully", Ansi.Color.GREEN);
      return false;
    } catch (Exception e) {
      throw new UserInputException("Invalid input: " + e.getMessage());
    }

  }

  @Override
  public String getDescription() {
    return "Add grocery to application";
  }
}
