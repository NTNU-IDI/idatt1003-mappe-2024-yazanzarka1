package org.ntnu.application.commands;

import java.util.List;
import org.fusesource.jansi.Ansi.Color;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.StorageEntry;
import org.ntnu.food.StorageUnit;

public class RemoveGroceryFromStorageUnitCommand implements Command {

	final InputHandler inputHandler;
	final StorageUnit storageUnit;
	final DisplayManager displayManager;
	public RemoveGroceryFromStorageUnitCommand(StorageUnit storageUnit){
		inputHandler = new InputHandler();
		this.storageUnit = storageUnit;
		displayManager = new DisplayManager();
	}

	@Override
	public Boolean execute() {
		storageUnit.displayGroceries();
		try {
			String storageEntryName = inputHandler.getInput("Enter name of grocery to remove: ");

			List<StorageEntry> storageEntries =  storageUnit.findGrocery(storageEntryName);
			if(storageEntries.isEmpty()) {
				displayManager.showColoredMessage("Error: Grocery not found", Color.RED);
				return true;
			}

			if (storageEntries.size() > 1) {
				displayManager.showColoredMessage("Error: Multiple groceries found", Color.RED);
				return true;
			}

			float quantity = Float.parseFloat(inputHandler.getInput("Enter quantity to remove: "));


			storageUnit.removeGrocery(storageEntries.getFirst(), quantity);
			displayManager.showColoredMessage("Grocery removed successfully", Color.GREEN);
			return false;
		} catch (Exception e) {
			displayManager.showColoredMessage(String.format("Error: %s", e.getMessage()), Color.RED);
			return false;
		}
	}

	@Override
	public String getDescription() {
		return "Remove grocery from storage unit";
	}
}
