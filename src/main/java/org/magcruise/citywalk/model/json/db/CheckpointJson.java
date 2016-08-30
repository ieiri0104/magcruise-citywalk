package org.magcruise.citywalk.model.json.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CheckpointJson {
	private String id;
	private String name;
	private String label;
	private double lat;
	private double lon;
	private List<String> checkpointGroupIds = new ArrayList<>();

	public CheckpointJson() {
	}

	public CheckpointJson(String id, String name, String label, double lat, double lon,
			List<String> checkPointGroupIds) {
		this.id = id;
		this.name = name;
		this.label = label;
		this.lat = lat;
		this.lon = lon;
		this.checkpointGroupIds.addAll(checkPointGroupIds);
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public void setCheckpointGroupIds(List<String> checkpointGroupIds) {
		this.checkpointGroupIds = checkpointGroupIds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
