package hu.bp.sudokucreator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsedElementsTest {
	UsedElements<Integer> usedElements;

	@BeforeEach
	void init() {
		usedElements = new UsedElements<>(2);
	}

	@Test
	void usedElements() {
		assertFalse(usedElements.contains(1, 0));

		usedElements.add(1, 0);

		check(1, 2, 0, 1);

		usedElements.add(2, 1);
		check(2, 3, 1, 0);

		usedElements.remove(2, 1);

		assertFalse(usedElements.contains(2, 1));
	}

	void check(int element, int otherElement, int index, int otherIndex) {
		assertFalse(usedElements.contains(element, otherIndex));
		assertFalse(usedElements.contains(otherElement, index));
		assertTrue(usedElements.contains(element, index));
	}

}