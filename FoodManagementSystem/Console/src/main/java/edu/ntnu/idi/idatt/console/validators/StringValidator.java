package edu.ntnu.idi.idatt.console.validators;

import edu.ntnu.idi.idatt.console.InputValidator;

/**
 * A validator for integers.
 */
public class StringValidator implements InputValidator<String> {

  private final String errorMessage;
  private final int min;
  private final int max;

  /**
   * Create a new StringValidator with a custom error message and a min and max length.
   *
   * @param errorMessage the error message to show if validation fails
   * @param min          the minimum length of the string
   * @param max          the maximum length of the string
   */
  public StringValidator(String errorMessage, int min, int max) {
    this.errorMessage = errorMessage;
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean validateInput(String input) {
    if (input == null || input.isBlank()) {
      return false;
    }
    return input.length() >= min && input.length() <= max;


  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

}
