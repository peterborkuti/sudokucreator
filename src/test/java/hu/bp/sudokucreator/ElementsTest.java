package hu.bp.sudokucreator;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ElementsTest {
	@Test
	void actualIsEmptyBeforeFirstUse() {
		Elements elements = new Elements(9);
		assertEquals(Elements.EMPTY_ELEMENT, elements.actual());
	}

	@Test
	void containsGoodNumberOfElements() {
		Elements elements = new Elements(9);
		UsedNumbers<Integer> usedNumbers = new UsedNumbers<>(9);

		for (int i = 0; i < 9; i++) {
			assertNotEquals(Elements.EMPTY_ELEMENT, elements.findNext(new Place(0, 0), usedNumbers));
		}

		assertEquals(Elements.EMPTY_ELEMENT, elements.findNext(new Place(0, 0), usedNumbers));
	}

	@Test
	void actualIsThePreviousNext() {
		Elements elements = new Elements(9, Comparator.comparingInt(SudokuElement::getValue));
		UsedNumbers<Integer> usedNumbers = new UsedNumbers<>(9);

		elements.findNext(new Place(0, 0), usedNumbers);
		assertEquals(0, elements.actual().getValue());
	}

	@Test
	void firstElementIsZeroWithoutSorting() {
		Elements elements = new Elements(9, Comparator.comparingInt(SudokuElement::getValue));
		UsedNumbers<Integer> usedNumbers = new UsedNumbers<>(9);

		assertEquals(0, elements.findNext(new Place(0,0), usedNumbers).getValue());
	}
}
