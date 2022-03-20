package hu.bp.sudokucreator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UsedElements<T> {
	private final List<Set<T>> elements;

	public UsedElements(int size) {
		elements = IntStream.range(0, size).mapToObj(i -> new HashSet<T>(size)).collect(Collectors.toList());
	}

	public boolean contains(T element, int index) {
		return elements.get(index).contains(element);
	}

	public void add(T element, int index) {
		elements.get(index).add(element);
	}

	public void remove(T element, int index) {
		elements.get(index).remove(element);
	}

	@Override
	public String toString() {
		return IntStream.range(0, elements.size()).mapToObj(i -> i + ":" + elements.get(i))
				.collect(Collectors.joining(","));
	}
}
