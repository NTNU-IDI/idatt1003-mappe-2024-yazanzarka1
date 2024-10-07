package org.ntnu.food;

import org.ntnu.units.Unit;

/**
 *
 */
public class Grocery {

  final String groceryName;
  final Unit unit;
  public float pricePerUnit;

  /**
   * @param itemName
   * @param unit
   * @param pricePerUnit
   */
  public Grocery(String itemName, Unit unit, float pricePerUnit) {
    this.groceryName = itemName;
    this.unit = unit;
    this.pricePerUnit = pricePerUnit;
  }

  /**
   * @return String Grocery Name
   */
  public String getGroceryName() {
    return groceryName;
  }


  /**
   * @return Unit Grocery's unit
   */
  public Unit getUnit() {
    return unit;
  }


  /**
   * @return float PricePerUnit
   */
  public float getPricePerUnit() {
    return pricePerUnit;
  }

}
