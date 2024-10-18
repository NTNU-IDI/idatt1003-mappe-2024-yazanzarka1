package edu.ntnu.idi.idatt.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandRegistryTest {

  private CommandRegistry commandRegistry;
  private MenuContext mainMenu;

  @BeforeEach
  void setUp() {
    commandRegistry = new CommandRegistry();
    mainMenu = new MenuContext("Main Menu", "main-menu");

    Command mockCommand = new Command() {
      @Override
      public Boolean execute() {
        return false;
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
    commandRegistry.switchContext("main-menu");
    assertEquals(mainMenu, commandRegistry.getCurrentContext(),
        "Should switch to the correct context");
  }

  @Test
  void testSwitchToNonExistentContext() {
    commandRegistry.switchContext("NonExistent");
    assertNull(commandRegistry.getCurrentContext(),
        "Should return null when switching to a non-existent context");
  }

  @Test
  void testExecuteCommandInContext() {
    commandRegistry.switchContext("main-menu");
    commandRegistry.executeCommand("test");

  }

  @Test
  void testExecuteInvalidCommand() {
    commandRegistry.switchContext("main-menu");
    commandRegistry.executeCommand("invalid");

  }
}