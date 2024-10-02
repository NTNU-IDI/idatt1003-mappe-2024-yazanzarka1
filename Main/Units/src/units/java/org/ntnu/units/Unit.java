package org.ntnu.units;
import org.
public abstract class Unit {
    // unit name - KG, Liter
    String unitName;
    float value;

    /**
     * getter for unit value
     * @return float
     */
    public float getValue(){
        return value;
    }

    /**
     * getter for unitName: KG, Liter
     * @return
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * adds to units' value
     * @param Unit otherUnit
     */
    public void plus(Unit otherUnit){

        if(this.unitName == otherUnit.unitName){
            throw new IllegalArgumentException()
        }
        this.value += otherUnit.value;
    }

    /**
     * substracts from unit's value
     * @param Unit otherUnit
     */
    public void subtract(Unit otherUnit){
        this.value -= otherUnit.value;
    }
}
