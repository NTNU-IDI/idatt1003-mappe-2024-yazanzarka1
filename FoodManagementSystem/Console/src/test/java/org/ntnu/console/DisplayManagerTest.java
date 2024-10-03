package org.ntnu.console;

import org.fusesource.jansi.Ansi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayManagerTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private DisplayManager displayManager;

	@BeforeEach
	public void setUp() {
		// Redirect system output to a byte stream for testing
		System.setOut(new PrintStream(outContent));
		displayManager = new DisplayManager();
	}

	@AfterEach
	public void tearDown() {
		System.setOut(originalOut); // Reset the system output
		displayManager.shutdown();
	}




}
