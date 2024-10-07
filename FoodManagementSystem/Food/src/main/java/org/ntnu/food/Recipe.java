package org.ntnu.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ntnu.console.DisplayManager;

public class Recipe {

  String name;
  String description;
  HashMap<Grocery, Float> groceries;
  DisplayManager displayManager;

  public Recipe(String name, String description, HashMap<Grocery, Float> groceries) {
    this.name = name;
    this.groceries = groceries;
    this.description = description;
    displayManager = new DisplayManager();
  }

  public Recipe(String name, String description) {
    this.name = name;
    this.groceries = new HashMap<>();
    this.description = description;
    displayManager = new DisplayManager();
  }

  public void addGrocery(Grocery grocery, float amount) {
    if (groceries.containsKey(grocery)) {
      groceries.put(grocery, groceries.get(grocery) + amount);
      return;
    }
    groceries.put(grocery, amount);
  }

  public HashMap<Grocery, Float> getGroceries() {
    return groceries;
  }

  public void displayRecipe() {
    List<String> headers = List.of("Grocery", "Unit", "Amount");
    List<List<String>> groceriesList = new ArrayList<>();
    for (Grocery grocery : groceries.keySet()) {
      groceriesList.add(List.of(grocery.getGroceryName(), grocery.getUnit().getUnitName(),
          groceries.get(grocery).toString()));
    }
    displayManager.printTable(headers, groceriesList);
  }

}
