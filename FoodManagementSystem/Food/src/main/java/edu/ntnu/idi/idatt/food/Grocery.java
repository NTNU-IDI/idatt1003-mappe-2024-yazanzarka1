package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.units.Unit;

/**
 * Grocery class represent a grocery that can be stored in a recipe or a storage unit.
 */
public class Grocery {

  final String groceryName;
  final Unit unit;
  final float pricePerUnit;

  /**
   * Public Grocery constructor.
   *
   * @param groceryName grocery name
   * @param unit SI unit
   * @param pricePerUnit price per unit NOK/KG for example
   */
  public Grocery(String groceryName, Unit unit, float pricePerUnit) {
    this.groceryName = groceryName;
    this.unit = unit;
    this.pricePerUnit = pricePerUnit;
  }

  /**
   * Grocery's name getter.
   *
   * @return String Grocery Name
   */
  public String getGroceryName() {
    return groceryName;
  }


  /**
   * Grocery's unit getter.
   *
   * @return Unit Grocery's unit
   */
  public Unit getUnit() {
    return unit;
  }


  /**
   * Grocery's price per unit getter.
   *
   * @return float PricePerUnit
   */
  public float getPricePerUnit() {
    return pricePerUnit;
  }

}
