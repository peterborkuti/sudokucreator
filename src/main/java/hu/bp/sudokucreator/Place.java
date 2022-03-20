package hu.bp.sudokucreator;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Place {
	public static final Place EMPTY = new Place(-1, -1);
	private final int row;
	private final int col;

	public boolean isEmpty() {
		return row < 0 || col < 0;
	}

	public Place next(int size) {
		int nextRow = row;
		int nextCol = col + 1;
		if (nextCol >= size) {
			nextCol = 0;
			nextRow++;
		}

		return nextRow >= size ? EMPTY : new Place(nextRow, nextCol);
	}
}
