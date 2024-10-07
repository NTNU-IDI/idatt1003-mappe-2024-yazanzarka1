package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.food.GroceryManager;

public class DisplayGroceriesCommand implements Command {

    final GroceryManager groceryManager;
    public DisplayGroceriesCommand(GroceryManager groceryManager) {
        this.groceryManager = groceryManager;
    }

    @Override
    public Boolean execute() {
        groceryManager.displayGroceries();
        return false;
    }

    @Override
    public String getDescription() {
        return "Display Available Groceries";
    }
}

