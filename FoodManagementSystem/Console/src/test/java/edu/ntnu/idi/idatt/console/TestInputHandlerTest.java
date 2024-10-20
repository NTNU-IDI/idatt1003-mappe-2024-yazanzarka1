package edu.ntnu.idi.idatt.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestInputHandlerTest {

  TestInputHandler testInputHandler;

  @BeforeEach
  void setUp() {
    testInputHandler = new TestInputHandler(new String[] {"test", "test2", "test3", "test4"});
  }

  @Test
  void getInputTest() {
    assertEquals("test", testInputHandler.getInput());
    assertEquals("test2", testInputHandler.getInput());
    assertEquals("test3", testInputHandler.getInput());
    assertEquals("test4", testInputHandler.getInput());
    assertThrows(IllegalStateException.class, () -> testInputHandler.getInput());
  }
}