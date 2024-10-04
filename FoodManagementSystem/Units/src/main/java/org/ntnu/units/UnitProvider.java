package org.ntnu.units;

import java.util.ArrayList;
import java.util.List;

public class UnitProvider {
    List<Unit> units;
    public UnitProvider() {
        units = new ArrayList<Unit>();
        units.add(new Gram());
        units.add(new Piece());
        units.add(new Liter());
    }

    /**
     *
     * @return Units
     */
    public List<Unit> getUnits() {
        return units;
    }
}
