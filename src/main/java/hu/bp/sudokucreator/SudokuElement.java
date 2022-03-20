package hu.bp.sudokucreator;

import lombok.Value;

@Value
public class SudokuElement implements Element<Integer> {
	Integer element;

	public SudokuElement(Integer v) {
		this.element = v;
	}

	public SudokuElement() {
		this.element = emptyValue();
	}

	@Override
	public Integer emptyValue() {
		return -1;
	}

	@Override
	public Integer getValue() {
		return element;
	}
}
