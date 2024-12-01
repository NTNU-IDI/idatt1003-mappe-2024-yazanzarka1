package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.food.constants.GroceryConstants;
import edu.ntnu.idi.idatt.units.Unit;
import java.util.Objects;

/**
 * Grocery class represent a grocery that can be stored in a recipe or a storage unit. Grocery has a
 * name, unit and price per unit. Grocery is used in StorageEntry and Recipe. Grocery is immutable.
 *
 * @see StorageEntry
 */
public class Grocery implements Comparable<Grocery> {

  private final String groceryName;
  private final Unit unit;
  private float pricePerUnit;

  /**
   * Public Grocery constructor.
   *
   * @param groceryName  grocery name
   * @param unit         SI unit
   * @param pricePerUnit price per unit NOK/KG for example
   */
  public Grocery(
      String groceryName,
      Unit unit,
      float pricePerUnit) {
    if (groceryName == null || groceryName.isBlank()) {
      throw new IllegalArgumentException("Grocery name cannot be null or blank");
    }
    if (groceryName.length() > GroceryConstants.MAX_GROCERY_NAME_LENGTH) {
      throw new IllegalArgumentException(
          String.format("Grocery name cannot be more than %s characters",
              GroceryConstants.MAX_GROCERY_NAME_LENGTH));
    }
    if (unit == null) {
      throw new IllegalArgumentException("Unit cannot be null");
    }
    this.groceryName = groceryName;
    this.unit = unit;
    setPricePerUnit(pricePerUnit);
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

  /**
   * Grocery's price per unit setter.
   *
   * @param pricePerUnit price per unit
   * @return float newPricePerUnit
   * @throws IllegalArgumentException if pricePerUnit is less than or equal to 0
   */
  public float setPricePerUnit(float pricePerUnit) {
    if (pricePerUnit <= GroceryConstants.MIN_PRICE_PER_UNIT) {
      throw new IllegalArgumentException(
          String.format("Price per unit cannot be less than %.2f NOK",
              GroceryConstants.MIN_PRICE_PER_UNIT));
    }
    if (pricePerUnit >= GroceryConstants.MAX_PRICE_PER_UNIT) {
      throw new IllegalArgumentException(
          String.format("Price per unit cannot be more than %.2f NOK",
              GroceryConstants.MAX_PRICE_PER_UNIT));
    }
    this.pricePerUnit = pricePerUnit;
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
   * Compares this object with the specified object for order.  Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified
   * object.
   *
   * <p>The implementor must ensure {@link Integer#signum
   * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for all {@code x} and {@code y}.
   * (This implies that {@code x.compareTo(y)} must throw an exception if and only if
   * {@code y.compareTo(x)} throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code
   * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z)) == signum(y.compareTo(z))}, for
   * all {@code z}.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   * or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it from being compared to
   *                              this object.
   * @apiNote It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements
   * the {@code Comparable} interface and violates this condition should clearly indicate this fact.
   * The recommended language is "Note: this class has a natural ordering that is inconsistent with
   * equals."
   */
  @Override
  public int compareTo(Grocery o) {
    return this.groceryName.compareTo(o.groceryName);
  }
}
