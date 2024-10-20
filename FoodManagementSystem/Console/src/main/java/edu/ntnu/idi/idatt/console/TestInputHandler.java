package edu.ntnu.idi.idatt.console;

/**
 * A test input handler that returns a predefined list of inputs for testing purposes.
 * All inputs are returned in the order they are provided.
 * If there are no more inputs to return, an IllegalStateException is thrown.
 */
public class TestInputHandler extends InputHandler {

  private final String[] inputs;
  private int index = 0;

  /**
   * Creates a new test input handler with the given list of inputs.
   *
   * @param inputs the list of inputs to return
   */
  public TestInputHandler(String[] inputs) {
    this.inputs = inputs;
  }

  @Override
  public String getInput() {
    if (index < inputs.length) {
      return inputs[index++];
    }
    throw new IllegalStateException("No more inputs");
  }


  @Override
  public String getInput(String prompt) {
    System.out.print(prompt);
    return getInput();
  }

}
