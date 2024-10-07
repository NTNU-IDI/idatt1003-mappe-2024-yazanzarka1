package org.ntnu.console;

/**
 * Command blueprint
 */
public interface Command {


	/**
	 * @return Boolean redisplay commands in menu-contexts if true
	 */
	Boolean execute();

	/**
	 * @return String defines the commands description
	 */
	String getDescription();
}
