package org.magcruise.citywalk.model.content;

public class TaskContent extends Content<TaskContent> {

	protected boolean checkIn = false;

	public TaskContent() {
	}

	public TaskContent(boolean checkpoint) {
		this.checkIn = checkpoint;
	}

	public boolean isCheckIn() {
		return checkIn;
	}

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

}
