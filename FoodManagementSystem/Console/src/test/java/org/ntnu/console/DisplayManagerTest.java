package org.ntnu.console;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisplayManagerTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private DisplayManager displayManager;

	@BeforeEach
	void setUp() {
		AnsiConsole.systemInstall();
		displayManager = new DisplayManager();
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	void tearDown() {
		System.setOut(originalOut);
		AnsiConsole.systemUninstall();
	}

	@Test
	@DisplayName("Test Show Colored Message")
	void testShowColoredMessage() {
		displayManager.showColoredMessage("Test Message", Ansi.Color.RED);
		assertEquals(Ansi.ansi().fg(Ansi.Color.RED).a("Test Message").reset().toString().trim(),
				outContent.toString().trim());
	}

	@Test
	@DisplayName("Test Show Message")
	void testShowMessage() {
		displayManager.showMessage("Test Message");
		assertEquals("Test Message", outContent.toString().trim());
	}

	@Test
	@DisplayName("Test Show Same Line Message")
	void testShowSameLineMessage() {
		displayManager.showSameLineMessage("Test Message");
		assertEquals("Test Message", outContent.toString());
	}


	@Test
	@DisplayName("Test Show Fancy Message")
	void testShowFancyMessage() {
		displayManager.showFancyMessage("Fancy Message");
		String expectedOutput = Ansi.ansi().bg(Ansi.Color.BLUE).fg(Ansi.Color.WHITE).a("**** ").reset()
				.fg(Ansi.Color.YELLOW).a("Fancy Message").reset().bg(Ansi.Color.BLUE)
				.fg(Ansi.Color.WHITE).a(" ****").reset().toString();
		assertEquals(expectedOutput.trim(), outContent.toString().trim());
	}
}