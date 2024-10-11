package org.ntnu.console;

/**
 * Command blueprint.
 */
public interface Command {


  /**
   * Execute command.
   *
   * @return Boolean redisplay commands in menu-contexts if true
   */
  Boolean execute();

  /**
   * Get command description.
   *
   * @return String defines the commands description
   */
  String getDescription();
}
