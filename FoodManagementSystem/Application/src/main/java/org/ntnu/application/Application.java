package org.ntnu.application;

import org.ntnu.application.core.Bootstrap;
import org.ntnu.console.InputHandler;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.CommandRegistry;

/**
 * Entry point for the application
 * This class contains the main method and will initialize the application
 * and start the main loop
 */

public class Application {

	public static void main(String[] args) {
		/**
		 * Initialize the application
		 */
		InputHandler inputHandler = new InputHandler();
		DisplayManager displayManager = new DisplayManager();

		/**
		 * Bootstrap the application's Command Registry
		 * This will initialize all the commands and menus
		 */
		CommandRegistry commandRegistry = Bootstrap.initCommandRegistry();
		Boolean displayMenu = false;

		/**
		 * Main loop
		 */
		while (true) {
			if (displayMenu){
				commandRegistry.getCurrentContext().displayMenu();
			}
			String input = inputHandler.getInput();

			// Check for help command
			if ("help".equalsIgnoreCase(input)) {
				displayManager.showMessage(commandRegistry.getCurrentContext().getName() + " - Help");
				commandRegistry.getCurrentContext().displayMenu();
				continue;
			}

			// Check for exit command
			if ("exit".equalsIgnoreCase(input)) {
				displayManager.showMessage("Exiting application...");
				break;
			}

			// Execute the entered command
			displayMenu = commandRegistry.executeCommand(input);
		}
	}

}
