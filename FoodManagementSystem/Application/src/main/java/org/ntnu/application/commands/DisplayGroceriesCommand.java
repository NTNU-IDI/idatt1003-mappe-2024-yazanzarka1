package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.food.GroceryManager;
import org.ntnu.food.StorageUnit;

public class DisplayGroceriesCommand implements Command {

    GroceryManager groceryManager;
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

