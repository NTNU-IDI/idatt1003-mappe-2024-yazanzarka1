package edu.ntnu.idi.idatt.console;

/**
 * Command blueprint.
 *
 * @author yazanzarka
 * @see CommandRegistry
 * @since 0.0.1
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
