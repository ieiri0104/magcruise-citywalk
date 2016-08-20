package org.magcruise.citywalk.model.json;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CheckpointJson {

	private String id;
	private String name;
	private String label;
	private double lat;
	private double lon;

	private CheckinJson checkin;
	private TaskJson task;

	public CheckpointJson() {
	}

	public CheckpointJson(String id, String name, String label, double lat, double lon,
			CheckinJson checkin, TaskJson task) {
		this.id = id;
		this.name = name;
		this.label = label;
		this.lat = lat;
		this.lon = lon;
		this.checkin = checkin;
		this.task = task;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public TaskJson getTask() {
		return task;
	}

	public void setTask(TaskJson task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public CheckinJson getCheckin() {
		return checkin;
	}

	public void setCheckin(CheckinJson checkin) {
		this.checkin = checkin;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
