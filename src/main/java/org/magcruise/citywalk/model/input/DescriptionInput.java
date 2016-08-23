package org.magcruise.citywalk.model.input;

public class DescriptionInput extends Input {

	private String value;

	public DescriptionInput() {
	}

	public DescriptionInput(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
