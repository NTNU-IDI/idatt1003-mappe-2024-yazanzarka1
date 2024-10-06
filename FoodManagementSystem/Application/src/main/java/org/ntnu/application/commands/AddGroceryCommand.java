package org.ntnu.application.commands;

import java.util.List;
import org.fusesource.jansi.Ansi;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.Grocery;
import org.ntnu.food.GroceryManager;
import org.ntnu.units.Unit;
import org.ntnu.units.UnitProvider;

public class AddGroceryCommand implements Command {

	GroceryManager groceryManager;
	InputHandler inputHandler;
	DisplayManager displayManager;
	UnitProvider unitProvider;

	public AddGroceryCommand(GroceryManager groceryManager) {
		this.groceryManager = groceryManager;
		this.inputHandler = new InputHandler();
		this.displayManager = new DisplayManager();
		this.unitProvider = new UnitProvider();
	}

	@Override
	public Boolean execute() {
		try {
			String groceryName = inputHandler.getInput("Enter Grocery Name: ");

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

			return true;
		} catch (Exception e) {
			displayManager.showColoredMessage(String.format("ERROR: %s", e.getMessage()), Ansi.Color.RED);
			return true;
		}
	}

	@Override
	public String getDescription() {
		return "Add grocery to application";
	}
}
