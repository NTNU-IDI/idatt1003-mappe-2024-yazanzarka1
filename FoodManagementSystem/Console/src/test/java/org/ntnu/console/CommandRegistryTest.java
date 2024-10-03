package org.ntnu.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandRegistryTest {
	private CommandRegistry commandRegistry;
	private MenuContext mainMenu;

	@BeforeEach
	void setUp() {
		commandRegistry = new CommandRegistry();
		mainMenu = new MenuContext("Main Menu");

		Command mockCommand = new Command() {
			@Override
			public void execute() {
				System.out.println("Mock Command Executed");
			}

			@Override
			public String getDescription() {
				return "Mock Command";
			}
		};

		mainMenu.addCommand("test", mockCommand);
		commandRegistry.addContext(mainMenu);
	}

	@Test
	void testSwitchContext() {
		commandRegistry.switchContext("Main Menu");
		assertEquals(mainMenu, commandRegistry.getCurrentContext(), "Should switch to the correct context");
	}

	@Test
	void testSwitchToNonExistentContext() {
		commandRegistry.switchContext("NonExistent");
		assertNull(commandRegistry.getCurrentContext(), "Should return null when switching to a non-existent context");
	}

	@Test
	void testExecuteCommandInContext() {
		commandRegistry.switchContext("Main Menu");
		commandRegistry.executeCommand("test");

	}

	@Test
	void testExecuteInvalidCommand() {
		commandRegistry.switchContext("Main Menu");
		commandRegistry.executeCommand("invalid");

	}
}