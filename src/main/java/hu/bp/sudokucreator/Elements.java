package hu.bp.sudokucreator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class Elements {
	public static final SudokuElement EMPTY_ELEMENT = new SudokuElement();

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private final Deque<SudokuElement> sudokuElements;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private SudokuElement actualElement = EMPTY_ELEMENT;

	private static final Random rnd = new Random();

	public Elements(int size) {
		this(size, (a, b) -> a.equals(b) ? 0 : rnd.nextBoolean() ? 1 : -1);
	}

	public Elements(int size, Comparator<SudokuElement> comparator) {
		sudokuElements = IntStream.range(0, size).boxed().map(SudokuElement::new)
				.sorted(comparator)
				.collect(Collectors.toCollection(ArrayDeque::new));
	}

	public SudokuElement actual() {
		return actualElement;
	}

	private SudokuElement next() {
		if (sudokuElements.isEmpty()) return EMPTY_ELEMENT;

		actualElement = sudokuElements.poll();

		return actualElement;
	}

	public SudokuElement findNext(Place place, UsedNumbers<Integer> usedNumbers) {
		if (sudokuElements.isEmpty()) return EMPTY_ELEMENT;

		SudokuElement element = next();
		while (!element.isEmpty() && usedNumbers.contains(place, element.getValue())) {
			element = next();
		}

		return element;
	}
}
