package edu.ntnu.idi.idatt.console;

import java.util.List;

/**
 * A test input handler that returns a predefined list of inputs for testing purposes.
 * All inputs are returned in the order they are provided.
 * If there are no more inputs to return, an IllegalStateException is thrown.
 *
 * @see InputHandler in the console package
 * @since 0.0.1
 * @author yazanzarka
 */
public class TestInputHandler extends InputHandler {

  private final List<String> inputs;
  private int index = 0;

  /**
   * Creates a new test input handler with the given list of inputs.
   *
   * @param inputs the list of inputs to return
   */
  public TestInputHandler(List<String> inputs) {
    this.inputs = inputs;
  }

  @Override
  public String getInput() {
    if (index < inputs.size()) {
      return inputs.get(index++);
    }
    throw new IllegalStateException("No more inputs");
  }


  @Override
  public String getInput(String prompt) {
    System.out.print(prompt);
    return getInput();
  }

}
