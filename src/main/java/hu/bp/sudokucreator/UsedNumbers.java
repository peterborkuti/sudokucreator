package hu.bp.sudokucreator;

import lombok.Data;

@Data
public class UsedNumbers<T extends Integer> {
	private final int sqrt;
	private final int size;
	private final UsedElements<T> rows;
	private final UsedElements<T> cols;
	private final UsedElements<T> parts;

	public UsedNumbers(int size) {
		sqrt = (int)Math.sqrt(size);
		if (size < 4 || sqrt*sqrt != size) {
			throw new IllegalArgumentException("Size should be greater than 3 and square number");
		}

		this.size = size;

		rows = new UsedElements<>(size);
		cols = new UsedElements<>(size);
		parts = new UsedElements<>(size);

	}

	public void add(Place place, T num) {
		if (contains(place, num)) {
			throw new IllegalArgumentException(num + " already contained");
		}

		int partIndex = getPartIndex(place);

		rows.add(num, place.getRow());
		cols.add(num, place.getCol());
		parts.add(num, partIndex);
	}

	public void remove(Place place, T num) {
		int partIndex = getPartIndex(place);

		rows.remove(num, place.getRow());
		cols.remove(num, place.getCol());
		parts.remove(num, partIndex);
	}

	public boolean contains(Place place, T num) {
		int partIndex = getPartIndex(place);

		return rows.contains(num, place.getRow()) || cols.contains(num, place.getCol()) || parts.contains(num, partIndex);
	}

	public int getPartIndex(Place place) {
		return (place.getRow() / sqrt)*sqrt + (place.getCol() / sqrt);
	}

	@Override
	public String toString() {
		return "Row:" + rows + "\n col:" + cols + "\n parts" + parts;
	}
}
