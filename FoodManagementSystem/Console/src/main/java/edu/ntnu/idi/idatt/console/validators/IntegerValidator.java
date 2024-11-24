package edu.ntnu.idi.idatt.console.validators;

import edu.ntnu.idi.idatt.console.InputValidator;

public class IntegerValidator implements InputValidator<Integer> {

  private final String errorMessage;
  private final int min;
  private final int max;

  /**
   * Create a new IntegerValidator with a custom error message and a min and max value.
   *
   * @param errorMessage the error message to show if validation fails
   * @param min          the minimum value of the integer
   * @param max          the maximum value of the integer
   */
  public IntegerValidator(String errorMessage, int min, int max) {
    this.errorMessage = errorMessage;
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean validateInput(Integer input) {
    if (input == null) {
      return false;
    }
    return input >= min && input <= max;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

}
