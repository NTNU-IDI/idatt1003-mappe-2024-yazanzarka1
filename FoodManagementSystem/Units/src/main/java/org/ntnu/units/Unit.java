package org.ntnu.units;

public abstract class Unit {

	// unit name - KG, Liter
	String unitName;

	/**
	 * getter for unitName: KG, Liter
	 *
	 * @return String unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	@Override
	public String toString() {
		return unitName;
	}

}
