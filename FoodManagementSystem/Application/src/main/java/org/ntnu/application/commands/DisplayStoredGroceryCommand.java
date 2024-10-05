package org.ntnu.application.commands;

import org.ntnu.console.Command;
import org.ntnu.food.StorageUnit;

public class DisplayStoredGroceryCommand implements Command {

	StorageUnit storageUnit;
	public DisplayStoredGroceryCommand(StorageUnit storageUnit) {
		this.storageUnit = storageUnit;

	}

	@Override
	public Boolean execute() {
		storageUnit.displayGroceries();
		return  false;
	}

	@Override
	public String getDescription() {
		return "Display stored grocery command";
	}
}
