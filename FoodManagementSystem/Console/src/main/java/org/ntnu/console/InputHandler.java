package org.ntnu.console;

import java.util.Scanner;

public class InputHandler {
	private final Scanner scanner;

	public InputHandler() {
		this.scanner = new Scanner(System.in);
	}

	public String getInput() {
		System.out.print("Enter command: ");
		return scanner.nextLine().trim();
	}

	public String mockInput(String input) {
		return input.trim();
	}
}
