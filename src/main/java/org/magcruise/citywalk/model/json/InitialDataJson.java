package org.magcruise.citywalk.model.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class InitialDataJson {

	private List<CheckpointJson> checkpoints = new ArrayList<>();

	public InitialDataJson() {
	}

	public InitialDataJson(List<CheckpointJson> checkpoints) {
		this.checkpoints.addAll(checkpoints);
	}

	public List<CheckpointJson> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<CheckpointJson> elements) {
		this.checkpoints = elements;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
