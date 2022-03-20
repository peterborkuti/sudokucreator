package hu.bp.sudokucreator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsedNumbersTest {
	UsedNumbers<Integer> usedNumbers;

	@BeforeEach
	void init() {
		usedNumbers = new UsedNumbers<>(9);
	}

	@Test
	void leftTopCorner() {
		usedNumbers.add(new Place(0, 0), 1);

		assertTrue(usedNumbers.contains(new Place(0,0), 1));
		assertTrue(usedNumbers.contains(new Place(0,8), 1));
		assertTrue(usedNumbers.contains(new Place(8,0), 1));
		assertTrue(usedNumbers.contains(new Place(2,2), 1));

		assertFalse(usedNumbers.contains(new Place(3, 1), 1));
	}

	@Test
	void getPartIndex() {
		assertEquals(0, usedNumbers.getPartIndex(new Place(0,0)));
		assertEquals(4, usedNumbers.getPartIndex(new Place(3,3)));
		assertEquals(8, usedNumbers.getPartIndex(new Place(8,8)));
	}

	@Test
	void rightBottomCorner() {
		usedNumbers.add(new Place(8, 8), 1);

		assertTrue(usedNumbers.contains(new Place(8,8), 1));
		assertTrue(usedNumbers.contains(new Place(8,0), 1));
		assertTrue(usedNumbers.contains(new Place(0,8), 1));
		assertTrue(usedNumbers.contains(new Place(6,6), 1));

		assertFalse(usedNumbers.contains(new Place(5, 7), 1));
	}

}