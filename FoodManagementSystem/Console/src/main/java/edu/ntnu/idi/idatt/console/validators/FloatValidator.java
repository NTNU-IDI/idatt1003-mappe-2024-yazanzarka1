package edu.ntnu.idi.idatt.console.validators;

import edu.ntnu.idi.idatt.console.InputValidator;

/**
 * A validator for floats.
 */
public class FloatValidator implements InputValidator<Float> {

  private final String errorMessage;
  private final float min;
  private final float max;

  /**
   * Create a new FloatValidator with a custom error message and a min and max value.
   *
   * @param errorMessage the error message to show if validation fails
   * @param min          the minimum value of the float
   * @param max          the maximum value of the float
   */
  public FloatValidator(String errorMessage, float min, float max) {
    this.errorMessage = errorMessage;
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean validateInput(Float input) {
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
