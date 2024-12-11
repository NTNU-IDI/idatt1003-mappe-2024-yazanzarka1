package edu.ntnu.idi.idatt.console.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateValidatorTest {

  DateValidator dateValidator;

  @BeforeEach
  void setUp() {
    dateValidator = new DateValidator("Error");
  }

  @Test
  void validateInput() {
    assertTrue(dateValidator.validateInput(new Date()));
    assertFalse(dateValidator.validateInput(null));
  }

  @Test
  void getErrorMessage() {
    assertEquals("Error", dateValidator.getErrorMessage());
  }
}