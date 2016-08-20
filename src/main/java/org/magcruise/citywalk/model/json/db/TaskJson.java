package org.magcruise.citywalk.model.json.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaskJson {

	private String id;
	private List<String> checkpointIds = new ArrayList<>();
	private ContentJson content;


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<String> getCheckpointIds() {
		return checkpointIds;
	}


	public void setCheckpointIds(List<String> checkpointIds) {
		this.checkpointIds = checkpointIds;
	}


	public ContentJson getContent() {
		return content;
	}


	public void setContent(ContentJson content) {
		this.content = content;
	}

}
