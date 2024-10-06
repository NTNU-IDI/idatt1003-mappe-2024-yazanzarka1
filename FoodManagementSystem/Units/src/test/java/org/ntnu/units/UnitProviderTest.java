package org.ntnu.units;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class UnitProviderTest {

	@Test
	void getUnits() {
		UnitProvider unitProvider = new UnitProvider();
		List<Unit> units = unitProvider.getUnits();

		assertNotNull(units, "Units list should not be null");
		assertEquals(3, units.size(), "Units list should contain 3 elements");
		assertTrue(units.stream().anyMatch(unit -> unit instanceof Kilogram), "Units list should contain an instance of Kilogram");
		assertTrue(units.stream().anyMatch(unit -> unit instanceof Piece), "Units list should contain an instance of Piece");
		assertTrue(units.stream().anyMatch(unit -> unit instanceof Liter), "Units list should contain an instance of Liter");
	}
}