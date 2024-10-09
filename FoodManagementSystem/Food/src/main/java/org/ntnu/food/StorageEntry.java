package org.ntnu.food;

import java.util.Date;

/**
 * StorageEntry is responsible for storing the quantity and best before date of a grocery inside a
 * StorageUnit.
 */
public class StorageEntry extends Grocery {

  float quantity;
  Date bestBeforeDate;

  StorageEntry(Grocery grocery, float quantity, Date bestBeforeDate) {
    super(grocery.groceryName, grocery.unit, grocery.pricePerUnit);
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

}
