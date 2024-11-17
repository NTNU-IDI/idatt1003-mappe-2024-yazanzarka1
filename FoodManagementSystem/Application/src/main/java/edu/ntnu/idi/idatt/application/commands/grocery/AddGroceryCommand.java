package edu.ntnu.idi.idatt.application.commands.grocery;

import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.console.exceptions.UserInputException;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.units.Unit;
import edu.ntnu.idi.idatt.units.UnitProvider;
import java.util.List;
import org.fusesource.jansi.Ansi;

/**
 * Add grocery to GroceryManager.
 */
public class AddGroceryCommand implements Command {

  final GroceryManager groceryManager;
  final InputHandler inputHandler;
  final DisplayManager displayManager;
  final UnitProvider unitProvider;

  /**
   * Adds Grocery to GroceryManager.
   *
   * @param groceryManager Grocery manager
   * @param unitProvider Unit provider
   * @param displayManager Display manager
   * @param inputHandler Input handler
   *
   */
  public AddGroceryCommand(GroceryManager groceryManager, UnitProvider unitProvider, DisplayManager displayManager, InputHandler inputHandler) {
    this.groceryManager = groceryManager;
    this.inputHandler = inputHandler;
    this.displayManager = displayManager;
    this.unitProvider = unitProvider;
  }

  /**
   * Adds Grocery to GroceryManager.
   *
   * @param groceryManager Grocery manager
   */
  public AddGroceryCommand(GroceryManager groceryManager, InputHandler inputHandler) {
    this.groceryManager = groceryManager;
    this.inputHandler = inputHandler;
    this.displayManager = new DisplayManager();
    this.unitProvider = new UnitProvider();
  }



  @Override
  public Boolean execute() {

    try {
      final String groceryName = inputHandler.getInput("Enter Grocery Name: ");

      // Display available units
      List<Unit> units = unitProvider.getUnits();
      System.out.println("Choose a unit:");

      for (int i = 0; i < units.size(); i++) {
        System.out.println(i + 1 + ". " + units.get(i).getClass().getSimpleName());
      }

      int choice = Integer.parseInt(inputHandler.getInput("Enter Choice: "));

      if (choice < 1 || choice > units.size()) {
        throw new IndexOutOfBoundsException(
            choice + " is invalid. Must be between 1 and " + units.size());
      }

      // Get the chosen unit
      Unit selectedUnit = units.get(choice - 1);

      float groceryPricePerUnit = Float.parseFloat(inputHandler.getInput("Enter price per unit: "));
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
