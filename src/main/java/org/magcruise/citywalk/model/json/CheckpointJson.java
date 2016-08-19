package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.row.Checkpoint;

public class CheckpointJson {

	private String id;
	private String name;
	private double lat;
	private double lon;
	private List<String> checkpointGroupIds = new ArrayList<>();

	private CheckinJson checkin;
	private TaskJson task;

	public CheckpointJson() {
	}

	public CheckpointJson(Checkpoint checkpoint) {
		this.id = checkpoint.getId();
		this.name = checkpoint.getName();
		this.lat = checkpoint.getLat();
		this.lon = checkpoint.getLon();
		this.checkpointGroupIds.addAll(checkpoint.getCheckpointGroupIds());
		// TODO
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

	public List<String> getCheckpointGroupIds() {
		return checkpointGroupIds;
	}

	public void setCheckpointGroupIds(List<String> checkpointIds) {
		this.checkpointGroupIds = checkpointIds;
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

}
