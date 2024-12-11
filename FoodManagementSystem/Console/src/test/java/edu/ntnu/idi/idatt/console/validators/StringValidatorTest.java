package edu.ntnu.idi.idatt.console.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringValidatorTest {

  StringValidator stringValidator;

  @BeforeEach
  void setUp() {
    stringValidator = new StringValidator("Error", 1, 10);
  }

  @Test
  void testValidateInput() {
    assertTrue(stringValidator.validateInput("Test"));
    assertFalse(stringValidator.validateInput(""));
    assertFalse(stringValidator.validateInput(null));
    assertFalse(stringValidator.validateInput("TestTestTest"));
  }

  @Test
  void testGetErrorMessage() {
    assertEquals("Error", stringValidator.getErrorMessage());
  }
}