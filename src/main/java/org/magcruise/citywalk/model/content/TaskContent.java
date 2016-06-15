package org.magcruise.citywalk.model.content;

import org.magcruise.citywalk.model.JsonConstructiveObject;

public class TaskContent extends JsonConstructiveObject<TaskContent> {

	protected boolean checkIn = false;
	protected String label;

	public TaskContent() {
	}

	public TaskContent(String label, boolean checkIn) {
		this.label = label;
		this.checkIn = checkIn;
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
