package edu.ntnu.idi.idatt.application;

import edu.ntnu.idi.idatt.application.core.Bootstrap;
import edu.ntnu.idi.idatt.console.Application;

/**
 * Entry point for the application This class contains the main method and will initialize the
 * application and start the main loop.
 */

public class Main {

  /**
   * Main Main Function.
   *
   * @param args Main params
   */
  public static void main(String[] args) {
    Application application = new Application();

    application.init(Bootstrap.initCommandRegistry());
    application.start();


  }

}
