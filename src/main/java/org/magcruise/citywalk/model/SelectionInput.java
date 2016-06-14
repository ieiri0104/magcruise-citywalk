package org.magcruise.citywalk.model;

public class SelectionInput extends Input {

	private String value;

	public SelectionInput() {
	}

	public SelectionInput(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
