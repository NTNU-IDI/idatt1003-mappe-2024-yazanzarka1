package org.ntnu.food;

import java.util.Date;
import org.ntnu.console.DisplayManager;
import org.ntnu.units.Unit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageContainer {

    public String name;
    HashMap<String, ContainerGrocery> groceries;
    DisplayManager displayManager;

    public StorageContainer(String name){
      this.name = name;
      displayManager = new DisplayManager();
      groceries = new HashMap<>();
    }
    public void addGrocery(Grocery grocery, float quantity, Date bestBeforeDate) {
    };

    public void removeGrocery(Grocery grocery, Unit unit){

    };

    public void displayGroceries() {
        List<String> headers = List.of("Grocery", "Unit", "NOK / Unit", "Quantity", "B.B.D");
        List<List<String>> groceryList = new ArrayList<>();
        for (Map.Entry<String, ContainerGrocery> entry : groceries.entrySet()) {
            ContainerGrocery containerGrocery = entry.getValue();
            groceryList.add(List.of(containerGrocery.grocery.groceryName, containerGrocery.grocery.unit.getUnitName(), String.valueOf(containerGrocery.quantity), String.valueOf(containerGrocery.grocery.pricePerUnit), new SimpleDateFormat("dd.MM.yyyy").format(containerGrocery.bestBeforeDate)));
        }
        displayManager.printTable(headers, groceryList);

    }

}