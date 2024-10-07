package org.ntnu.application.commands;

import java.util.List;
import org.ntnu.console.Command;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.InputHandler;
import org.ntnu.food.StorageEntry;
import org.ntnu.food.StorageUnit;

public class SearchInStorageUnitCommand implements Command {

	final InputHandler inputHandler;
	final StorageUnit storageUnit;
	final DisplayManager displayManager;

	public SearchInStorageUnitCommand(StorageUnit storageUnit) {
		inputHandler = new InputHandler();
		this.storageUnit = storageUnit;
		displayManager = new DisplayManager();
	}

	/**
	 * @return Boolean rerender the menu-context commands
	 */
	@Override
	public Boolean execute() {
		System.out.println("Search in storage unit");

		String query = inputHandler.getInput("Enter grocery name: ");
		// search in storage unit
		List<StorageEntry> foundEntries = storageUnit.findGrocery(query);
		if (foundEntries.isEmpty()) {
			displayManager.showFancyMessage("No groceries found");
			return false;
		}
		storageUnit.displayGroceries(foundEntries);
		return false;
	}

	/**
	 * @return String Description of Command
	 */
	@Override
	public String getDescription() {
		return "Search in storage unit";
	}
}
