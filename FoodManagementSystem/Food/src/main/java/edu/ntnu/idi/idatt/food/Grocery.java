package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.Objects;

/**
 * Grocery class represent a grocery that can be stored in a recipe or a storage unit. Grocery has a
 * name, unit and price per unit. Grocery is used in StorageEntry and Recipe. Grocery is immutable.
 *
 * @author yazanzarka
 * @see StorageEntry
 * @since 0.0.1
 */
public class Grocery implements Comparable<Grocery> {

  private final String groceryName;
  private final Unit unit;
  private final float pricePerUnit;

  /**
   * Public Grocery constructor.
   *
   * @param groceryName  {@link String} grocery name
   * @param unit         {@link Unit} SI unit
   * @param pricePerUnit price per unit NOK/KG for example
   * @throws IllegalArgumentException if groceryName is null or blank, groceryName is more than 25
   * @throws IllegalArgumentException if unit is null
   * @throws IllegalArgumentException if pricePerUnit is less than 0.01 or more than 9999.99
   */
  public Grocery(String groceryName, Unit unit, float pricePerUnit) {
    // Check if grocery name is valid
    if (groceryName == null || groceryName.isBlank()) {
      throw new IllegalArgumentException("Grocery name cannot be null or blank");
    }
    // Check if grocery name is within limits
    if (groceryName.length() > GroceryConstants.MAX_GROCERY_NAME_LENGTH) {
      throw new IllegalArgumentException(
          String.format("Grocery name cannot be more than %s characters",
              GroceryConstants.MAX_GROCERY_NAME_LENGTH));
    }

    // Check if unit is valid
    if (unit == null) {
      throw new IllegalArgumentException("Unit cannot be null");
    }

    // Check if price per unit is within limits
    if (pricePerUnit < GroceryConstants.MIN_PRICE_PER_UNIT
        || pricePerUnit > GroceryConstants.MAX_PRICE_PER_UNIT) {
      throw new IllegalArgumentException(
          String.format("Price per unit cannot be less than %.2f NOK or more than %.2f NOK",
              GroceryConstants.MIN_PRICE_PER_UNIT, GroceryConstants.MAX_PRICE_PER_UNIT));
    }
    // Set values
    this.groceryName = groceryName;
    this.unit = unit;
    this.pricePerUnit = pricePerUnit;
  }


  /**
   * Grocery's name getter.
   *
   * @return {@link String} Grocery Name
   */
  public String getGroceryName() {
    return groceryName;
  }


  /**
   * Grocery's unit getter.
   *
   * @return {@link Unit} Grocery's unit
   */
  public Unit getUnit() {
    return unit;
  }


  /**
   * Grocery's price per unit getter.
   *
   * @return {@link Float} PricePerUnit
   */
  public float getPricePerUnit() {
    return pricePerUnit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Grocery grocery = (Grocery) o;
    return this.hashCode() == grocery.hashCode();

  }

  @Override
  public int hashCode() {
    return Objects.hash(groceryName.toLowerCase());
  }

  /**
   * Compare Grocery by name.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   */
  @Override
  public int compareTo(Grocery o) {
    return this.groceryName.compareTo(o.groceryName);
  }
}
