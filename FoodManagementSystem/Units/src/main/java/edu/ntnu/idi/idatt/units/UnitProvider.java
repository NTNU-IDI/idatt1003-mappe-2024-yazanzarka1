package edu.ntnu.idi.idatt.units;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides registered units for application.
 */
public class UnitProvider {

  final List<Unit> units;

  /**
   * Initiate a new UnitProvider with registered units.
   */
  public UnitProvider() {
    units = new ArrayList<>();
    units.add(new Kilogram());
    units.add(new Piece());
    units.add(new Liter());
  }

  /**
   * Get registered units.
   *
   * @return List of registered Unit units in the app
   */
  public List<Unit> getUnits() {
    return units;
  }
}
