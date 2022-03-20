package hu.bp.sudokucreator;

public interface Element<T extends Integer> {
	default boolean isEmpty() {
		return emptyValue().equals(getValue());
	}

	T emptyValue();
	T getValue();
}
