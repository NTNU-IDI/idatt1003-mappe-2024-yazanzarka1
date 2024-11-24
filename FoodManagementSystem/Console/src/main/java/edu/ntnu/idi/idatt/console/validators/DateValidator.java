package edu.ntnu.idi.idatt.console.validators;

import edu.ntnu.idi.idatt.console.InputValidator;
import java.util.Date;

public class DateValidator implements InputValidator<Date> {

  private final String errorMessage;

  /**
   * Create a new DateValidator with a custom error message and a regex.
   *
   * @param errorMessage the error message to show if validation fails
   */
  public DateValidator(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public boolean validateInput(Date input) {
    return input != null;
  }

  @Override
  public String getErrorMessage() {
    return errorMessage;
  }

}
