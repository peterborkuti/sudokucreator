package hu.bp.sudokucreator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Places {
	private final int size;
	private final List<Place> sudokuPlaces = new ArrayList<>();
	private int index = -1;

	public Places(int size) {
		this.size = size;

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				sudokuPlaces.add(new Place(row, col));
			}
		}
	}

	public Place next() {
		if (index >= size * size - 1) return Place.EMPTY;

		index++;

		return actual();
	}

	public Place actual() {
		return sudokuPlaces.get(index);
	}

	public Place previous() {
		if (index <= 0) return Place.EMPTY;

		index--;

		return actual();
	}
}
