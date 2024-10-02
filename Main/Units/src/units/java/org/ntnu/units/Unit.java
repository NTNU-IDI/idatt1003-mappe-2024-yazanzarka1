package org.ntnu.units;

public abstract class Unit {
    String unitName;
    float value;

    public float getValue(){
        return value;
    }

    public String getUnitName() {
        return unitName;
    }

    public void plus(Unit otherUnit){
        this.value += otherUnit.value;
    }

    public void subtract(Unit otherUnit){
        this.value -= otherUnit.value;
    }
}
