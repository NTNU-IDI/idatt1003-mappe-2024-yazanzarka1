package edu.ntnu.idi.idatt.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuContextTest {

  private MenuContext menuContext;
  private Command mockCommand;

  @BeforeEach
  void setUp() {
    menuContext = new MenuContext("Test Menu", "test-menu");
    mockCommand = new Command() {
      @Override
      public Boolean execute() {
        return false;
      }

      @Override
      public String getDescription() {
        return "Mock Command";
      }
    };
  }

  @Test
  void testAddAndGetCommand() {
    menuContext.addCommand("test", mockCommand);
    assertEquals(mockCommand, menuContext.getCommand("test"), "Command should be found in menu");
  }

  @Test
  void testGetNonExistentCommand() {
    assertNull(menuContext.getCommand("nonexistent"),
        "Should return null for a non-existent command");
  }
}
