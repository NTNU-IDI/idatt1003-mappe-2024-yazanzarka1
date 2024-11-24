package edu.ntnu.idi.idatt.food;

import edu.ntnu.idi.idatt.food.constants.StorageEntryConstants;
import java.util.Date;
import java.util.Objects;

/**
 * StorageEntry is responsible for storing the quantity and best before date of a grocery inside a
 * StorageUnit. StorageEntry is used in StorageUnit. StorageEntry is immutable. StorageEntry is
 * comparable.
 *
 * @see StorageUnit
 */
public class StorageEntry extends Grocery {

  private float quantity;
  private Date bestBeforeDate;

  StorageEntry(Grocery grocery, float quantity, Date bestBeforeDate) {
    super(grocery.getGroceryName(), grocery.getUnit(), grocery.getPricePerUnit());
    setQuantity(quantity);
    setBestBeforeDate(bestBeforeDate);
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
   * @throws IllegalArgumentException if quantity is less than 0
   */
  public void setQuantity(float quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity cannot be less than 0");
    }
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
   * @throws IllegalArgumentException if quantity is less than or equal to 0
   */
  public void addQuantity(float quantity) {
    if (quantity < StorageEntryConstants.MIN_QUANTITY) {
      throw new IllegalArgumentException("Quantity cannot be less than or equal to 0.0");
    }
    if (quantity > StorageEntryConstants.MAX_QUANTITY) {
      throw new IllegalArgumentException("Quantity cannot be greater than 999.0");
    }

    if (this.quantity + quantity > StorageEntryConstants.MAX_QUANTITY) {
      throw new IllegalArgumentException("Cannot store more than 999.0 of the same grocery");
    }
    this.quantity += quantity;
  }

  /**
   * subtract quantity from current quantity.
   *
   * @param quantity quantity to be removed
   * @throws IllegalArgumentException if quantity is less than or equal to 0
   */
  public void subtractQuantity(float quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be less than or equal to 0 ");
    }
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StorageEntry that = (StorageEntry) o;
    return this.hashCode() == that.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getGroceryName());
  }
}
