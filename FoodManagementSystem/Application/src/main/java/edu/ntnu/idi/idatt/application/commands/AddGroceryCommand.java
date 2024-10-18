package edu.ntnu.idi.idatt.application.commands;

import java.util.List;
import org.fusesource.jansi.Ansi;
import edu.ntnu.idi.idatt.console.Command;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.food.Grocery;
import edu.ntnu.idi.idatt.food.GroceryManager;
import edu.ntnu.idi.idatt.units.Unit;
import edu.ntnu.idi.idatt.units.UnitProvider;

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
   */
  public AddGroceryCommand(GroceryManager groceryManager) {
    this.groceryManager = groceryManager;
    this.inputHandler = new InputHandler();
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
        System.out.println("Invalid choice");
        return true;
      }

      // Get the chosen unit
      Unit selectedUnit = units.get(choice - 1);

      float groceryPricePerUnit = Float.parseFloat(inputHandler.getInput("Enter price per unit: "));

      groceryManager.addGrocery(new Grocery(groceryName, selectedUnit, groceryPricePerUnit));
      displayManager.showColoredMessage("Grocery added successfully", Ansi.Color.GREEN);

      return false;
    } catch (Exception e) {
      displayManager.showColoredMessage(String.format("ERROR: %s", e.getMessage()), Ansi.Color.RED);
      return false;
    }
  }

  @Override
  public String getDescription() {
    return "Add grocery to application";
  }
}
