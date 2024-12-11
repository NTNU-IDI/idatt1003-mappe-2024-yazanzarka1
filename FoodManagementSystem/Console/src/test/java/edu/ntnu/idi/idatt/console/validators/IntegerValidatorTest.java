package edu.ntnu.idi.idatt.console.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegerValidatorTest {

  IntegerValidator integerValidator;

  @BeforeEach
  void setUp() {
    integerValidator = new IntegerValidator("Error", 1, 10);
  }

  @Test
  void validateInput() {
    assertTrue(integerValidator.validateInput(5));
    assertFalse(integerValidator.validateInput(0));
    assertFalse(integerValidator.validateInput(11));
    assertFalse(integerValidator.validateInput(null));
  }

  @Test
  void getErrorMessage() {
    assertEquals("Error", integerValidator.getErrorMessage());
  }
}