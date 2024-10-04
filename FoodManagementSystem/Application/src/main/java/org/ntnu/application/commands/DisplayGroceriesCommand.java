package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.food.StorageContainer;

public class DisplayGroceriesCommand implements Command {

    StorageContainer storageContainer;
    public DisplayGroceriesCommand(StorageContainer storageContainer) {
        this.storageContainer = storageContainer;
    }

    @Override
    public Boolean execute() {
        storageContainer.displayGroceries();
        return false;
    }

    @Override
    public String getDescription() {
        return String.format("Display Groceries in %s", storageContainer.name);
    }
}

