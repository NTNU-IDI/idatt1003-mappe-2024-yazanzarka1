package org.ntnu.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {
	private InputHandler inputHandler;

	@BeforeEach
	void setUp() {
		inputHandler = new InputHandler();
	}

	@Test
	void testGetInput() {
		// Simulate user input
		String input = "mock command\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		String result = inputHandler.mockInput(input);
		assertEquals("mock command", result, "Should return the mocked input command");
	}
}