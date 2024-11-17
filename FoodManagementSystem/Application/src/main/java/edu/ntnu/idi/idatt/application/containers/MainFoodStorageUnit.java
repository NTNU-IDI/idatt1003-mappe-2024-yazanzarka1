package edu.ntnu.idi.idatt.application.containers;

import edu.ntnu.idi.idatt.console.DisplayManager;
import edu.ntnu.idi.idatt.food.StorageUnit;

/**
 * Main Food Storage Unit. This application has a main storage unit for simplicity. Commands are
 * built around handling different storage units.
 */
public class MainFoodStorageUnit extends StorageUnit {

  /**
   * Initiate an instance of MainFoodStorageUnit.
   */
  public MainFoodStorageUnit(DisplayManager displayManager) {
    super("Main Food StorageUnit", displayManager);
  }
}
