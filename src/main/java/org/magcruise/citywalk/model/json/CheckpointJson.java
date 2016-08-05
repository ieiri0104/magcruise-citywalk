package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.row.Checkpoint;

public class CheckpointJson {

	private String id;
	private double lat;
	private double lon;
	private List<String> checkpointGroupIds = new ArrayList<>();
	private boolean checkinType = false;
	private String checkinAnswerQr;

	private TaskJson task;

	public CheckpointJson() {
	}

	public CheckpointJson(Checkpoint checkpoint) {
		this.id = checkpoint.getId();
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

	public boolean isCheckinType() {
		return checkinType;
	}

	public void setCheckinType(boolean checkinType) {
		this.checkinType = checkinType;
	}

	public String getCheckinAnswerQr() {
		return checkinAnswerQr;
	}

	public void setCheckinAnswerQr(String checkinAnswerQr) {
		this.checkinAnswerQr = checkinAnswerQr;
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

}
