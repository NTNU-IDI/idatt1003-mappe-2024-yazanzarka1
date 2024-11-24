package edu.ntnu.idi.idatt.application;

import edu.ntnu.idi.idatt.application.core.Bootstrap;
import edu.ntnu.idi.idatt.console.Application;
import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.console.InputHandler;
import edu.ntnu.idi.idatt.units.UnitProvider;

/**
 * Entry point for the application This class contains the main method and will initialize the
 * application and start the main loop.
 */

public class Main {

  /**
   * Main Function.
   *
   * @param args Main params
   */
  public static void main(String[] args) {
    Application application = new Application();
    DisplayManager displayManager = new DisplayManager();
    InputHandler inputHandler = new InputHandler(displayManager);
    UnitProvider unitProvider = new UnitProvider();

    application.init(Bootstrap.initCommandRegistry(displayManager, inputHandler, unitProvider));
    application.start();


  }

}
