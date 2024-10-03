package org.ntnu;

import org.ntnu.menus.MainMenu;
import org.ntnu.console.InputHandler;
import org.ntnu.console.DisplayManager;


public class Application {

	public static void main(String[] args) {
		CommandRegistry commandRegistry = new CommandRegistry();
		InputHandler inputHandler = new InputHandler();
		DisplayManager displayManager = new DisplayManager();
		// Create and register menus
		MainMenu mainMenu = new MainMenu(commandRegistry);
		commandRegistry.addContext(mainMenu);

		// Set initial menu context
		commandRegistry.switchContext("main-menu");

		while (true) {
			displayManager.showFancyMessage("Welcome to NTNU");
			String input = inputHandler.getInput();

			// Check for exit command
			if ("exit".equalsIgnoreCase(input)) {
				displayManager.showMessage("Exiting application...");
				break;
			}

			// Execute the entered command
			commandRegistry.executeCommand(input);
		}
	}

}
