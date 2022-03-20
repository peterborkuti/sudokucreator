package hu.bp.sudokucreator;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SudokuCreator {
	private static final boolean RANDOMIZE = true;

	public static void main(String[] args) {
		SudokuCreator creator = new SudokuCreator();
		creator.create(9);
	}

	public void create(int size)  {
		UsedNumbers<Integer> usedNumbers = new UsedNumbers<>(size);
		Map<Place, Elements> game = new HashMap<>();
		Places places = new Places(size);

		Place place = places.next();

		while (!place.isEmpty()) {
			Elements elements = game.computeIfAbsent(place, (p) -> getNewElements(size));
			Element<Integer> actual = elements.actual();

			if (!actual.isEmpty()) {
				usedNumbers.remove(place, actual.getValue());
			}

			Element<Integer> element = elements.findNext(place, usedNumbers);

			if (element.isEmpty()) {
				game.remove(place);
				place = places.previous();
				continue;
			}

			usedNumbers.add(place, element.getValue());
			place = places.next();
		}

		printGame(game, size);

	}

	private Elements getNewElements(int size) {
		if (RANDOMIZE) {
			return new Elements(size);
		}

		return new Elements(size, Comparator.comparingInt(SudokuElement::getElement));
	}

	private void printGame(Map<Place, Elements> game, int size) {
		System.out.println("\n\n");
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				Elements elements = game.get(new Place(row, col));
				String val = String.format("%3s|", elements == null ? "" : elements.actual().getValue());
				System.out.print(val);
			}
			System.out.println();
		}
	}
}
