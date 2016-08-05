package org.magcruise.citywalk.model.content;

import org.magcruise.citywalk.model.JsonConstructiveObject;

public class TaskContent extends JsonConstructiveObject<TaskContent> {

	protected boolean checkin = false;
	protected String label;

	public TaskContent() {
	}

	public TaskContent(String label, boolean checkin) {
		this.label = label;
		this.checkin = checkin;
	}

	public boolean isCheckin() {
		return checkin;
	}

	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
