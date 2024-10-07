package org.ntnu.units;

import java.util.ArrayList;
import java.util.List;

public class UnitProvider {
    final List<Unit> units;
    public UnitProvider() {
        units = new ArrayList<>();
        units.add(new Kilogram());
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
