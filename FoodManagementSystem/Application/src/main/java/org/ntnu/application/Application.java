package org.ntnu.application;

import org.ntnu.application.core.Bootstrap;
import org.ntnu.console.InputHandler;
import org.ntnu.console.DisplayManager;
import org.ntnu.console.CommandRegistry;


public class Application {

	public static void main(String[] args) {
		InputHandler inputHandler = new InputHandler();
		DisplayManager displayManager = new DisplayManager();
		CommandRegistry commandRegistry = Bootstrap.initCommandRegistry();
		while (true) {
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
