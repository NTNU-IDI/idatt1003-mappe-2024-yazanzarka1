package org.ntnu.food;

import java.util.Date;

/**
 * StorageEntry is responsible for storing the quantity
 * and best before date of a grocery inside a StorageUnit.
 */
public class StorageEntry extends Grocery {
  float quantity;
  Date bestBeforeDate;

  StorageEntry(Grocery grocery, float quantity, Date bestBeforeDate) {
    super(grocery.groceryName, grocery.unit, grocery.pricePerUnit);
    this.quantity = quantity;
    this.bestBeforeDate = bestBeforeDate;
  }

  public float getQuantity() {
    return quantity;
  }

  public Date getBestBeforeDate() {
    return bestBeforeDate;
  }

  public void setQuantity(float quantity) {
    this.quantity = quantity;
  }

  public void setBestBeforeDate(Date bestBeforeDate) {
    this.bestBeforeDate = bestBeforeDate;
  }

  public void addQuantity(float quantity){
    this.quantity += quantity;
  }

  public void subtractQuantity(float quantity){
    this.quantity -= quantity;
  }

  public Boolean isExpired(){
    Date currentDate = new Date();
    return currentDate.after(bestBeforeDate);

  }

}
