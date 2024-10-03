package org.ntnu.units;

public abstract class Unit {

	// unit name - KG, Liter
	String unitName;
	float value;

	/**
	 * getter for unit value
	 *
	 * @return float value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * getter for unitName: KG, Liter
	 *
	 * @return String unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * adds to units' value
	 *
	 * @param Unit otherUnit
	 */
	public void plus(Unit otherUnit) {

		if (!this.unitName.equals(otherUnit.unitName)) {
			throw new IllegalArgumentException("You can't add two different units together");
		}
		this.value += otherUnit.value;
	}

	/**
	 * subtracts from unit's value
	 *
	 * @param Unit otherUnit
	 */
	public void subtract(Unit otherUnit) {
		if (!this.unitName.equals(otherUnit.unitName)) {
			throw new IllegalArgumentException("You can't subtract two different units together");
		}
		this.value -= otherUnit.value;
	}
}
