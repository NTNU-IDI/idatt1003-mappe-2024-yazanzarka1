package org.ntnu.units;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnitTest {

	private Liter liter;
	private Gram gram;

	@BeforeEach
	void setUp() {
		liter = new Liter(50);
		gram = new Gram(50);
	}

	@Test
	@DisplayName("Test value getter.")
	void getValue() {
      Assertions.assertAll(
          "Assert values return as expected from children classes",
          () -> Assertions.assertEquals(50, gram.getValue()),
          () -> Assertions.assertEquals(50, liter.getValue())
      );
	}

	@Test
	@DisplayName("Test unitName getter")
	void getUnitName() {
      Assertions.assertAll(
          "Assert unitName return as expected from children classes",
          () -> Assertions.assertEquals("gram", gram.getUnitName()),
          () -> Assertions.assertEquals("liter", liter.getUnitName())
      );
	}

	@Test
	@DisplayName("Test plus method adds value to unit.")
	void plus_addsValue() {
      liter.plus(new Liter(25));
			gram.plus(new Gram(30));

			Assertions.assertAll(
					"Assert plus add value to unit and stores in this",
					() -> Assertions.assertEquals(75, liter.getValue()),
					() -> Assertions.assertEquals(80, gram.getValue())
			);
	}

	@Test
	@DisplayName("Test plus method throws exception if units are different")
	void plus_throwsArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> liter.plus(new Gram(50)));
	}



	@Test
	@DisplayName("Test subtract method removes value from unit.")
	void subtract_removesValue() {
		liter.subtract(new Liter(5));
		gram.subtract(new Gram(15));

		Assertions.assertAll(
				"Assert plus add value to unit and stores in this",
				() -> Assertions.assertEquals(45, liter.getValue()),
				() -> Assertions.assertEquals(35, gram.getValue())
		);
	}

	@Test
	@DisplayName("Test subtract method throws exception if units are different")
	void subtract_throwsArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> liter.plus(new Gram(20)));
	}
}