package edu.ntnu.idi.idatt.console.validators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloatValidatorTest {

  FloatValidator floatValidator;

  @BeforeEach
  void setUp() {
    floatValidator = new FloatValidator("Error", 0.0f, 0.5f);
  }

  @Test
  void validateInput() {
    assertTrue(floatValidator.validateInput(0.25f));
    assertFalse(floatValidator.validateInput(0.75f));
    assertFalse(floatValidator.validateInput(null));
  }

  @Test
  void getErrorMessage() {
    assertEquals("Error", floatValidator.getErrorMessage());
  }
}