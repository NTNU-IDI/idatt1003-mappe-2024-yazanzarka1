package org.ntnu.console;

/**
 * Command blueprint
 */
public interface Command {
	Boolean execute();
	String getDescription();
}
