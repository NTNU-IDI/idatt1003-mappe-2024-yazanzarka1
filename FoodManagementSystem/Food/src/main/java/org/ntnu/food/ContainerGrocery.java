package org.ntnu.food;

import java.util.Date;

/**
 * ContainerGrocery is responsible for storing the quantity
 * and best before date of a grocery inside a StorageContainer.
 */
public class ContainerGrocery {
  Grocery grocery;
  float quantity;
  Date bestBeforeDate;

  ContainerGrocery(Grocery grocery, float quantity, Date bestBeforeDate) {
    this.grocery = grocery;
    this.quantity = quantity;
    this.bestBeforeDate = bestBeforeDate;

  }

}
