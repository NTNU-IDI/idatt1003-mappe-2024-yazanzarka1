package org.ntnu.console;

/**
 * Command blueprint
 */
public interface Command {
	void execute();
	String getDescription();
}
