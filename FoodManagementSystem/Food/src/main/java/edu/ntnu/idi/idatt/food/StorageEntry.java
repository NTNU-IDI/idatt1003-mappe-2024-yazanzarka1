package edu.ntnu.idi.idatt.food;

import java.util.Date;
import java.util.Objects;

/**
 * StorageEntry is responsible for storing the quantity and best before date of a grocery inside a
 * StorageUnit.
 * StorageEntry is used in StorageUnit.
 * StorageEntry is mutable.
 * StorageEntry is comparable.
 *
 * @see StorageUnit
 */
public class StorageEntry extends Grocery implements Comparable<StorageEntry> {

  float quantity;
  Date bestBeforeDate;

  StorageEntry(Grocery grocery, float quantity, Date bestBeforeDate) {
    super(grocery.getGroceryName(), grocery.getUnit(), grocery.getPricePerUnit());
    this.quantity = quantity;
    this.bestBeforeDate = bestBeforeDate;
  }

  /**
   * groceryName getter.
   *
   * @return float Grocery's quantity in a storage unit
   */
  public float getQuantity() {
    return quantity;
  }

  /**
   * Quantity setter.
   *
   * @param quantity new quantity
   */
  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }

  /**
   * bestBeforeDate getter.
   *
   * @return Date BestBeforeDate when the grocery expires
   */
  public Date getBestBeforeDate() {
    return bestBeforeDate;
  }

  /**
   * BestBeforeDate setter.
   *
   * @param bestBeforeDate new best before date.
   */
  public void setBestBeforeDate(Date bestBeforeDate) {
    this.bestBeforeDate = bestBeforeDate;
  }

  /**
   * add quantity to current quantity.
   *
   * @param quantity quantity to add to current quantity
   */
  public void addQuantity(float quantity) {
    this.quantity += quantity;
  }

  /**
   * subtract quantity from current quantity.
   *
   * @param quantity quantity to be removed
   */
  public void subtractQuantity(float quantity) {
    this.quantity -= quantity;
  }

  /**
   * check if the grocery is expired.
   *
   * @return Boolean is the grocery expired
   */
  public Boolean isExpired() {
    Date currentDate = new Date();
    return currentDate.after(bestBeforeDate);

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
   *  The recommended language is "Note: this class has a natural ordering that is inconsistent with
   * equals."
   */
  @Override
  public int compareTo(StorageEntry o) {
    return this.getGroceryName().compareTo(o.getGroceryName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StorageEntry storageEntry = (StorageEntry) o;
    return getGroceryName().equals(storageEntry.getGroceryName()) && getUnit().getUnitName()
        .equals(storageEntry.getUnit().getUnitName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getGroceryName(), getUnit().getUnitName());
  }
}
