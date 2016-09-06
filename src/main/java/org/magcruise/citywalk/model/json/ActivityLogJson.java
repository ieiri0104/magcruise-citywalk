package org.magcruise.citywalk.model.json;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.row.Activity;

public class ActivityLogJson {

	private long id;
	private String userId;

	private String checkpointId;
	private boolean checkin;
	private Date saved;

	public ActivityLogJson() {
	}

	public ActivityLogJson(Activity activity, boolean checkin) {
		this.id = activity.getId();
		this.userId = activity.getUserId();
		this.checkpointId = activity.getCheckpointId();
		this.saved = activity.getSaved();

	}

	public Date getSaved() {
		return saved;
	}

	public void setSaved(Date saved) {
		this.saved = saved;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCheckpointId() {
		return checkpointId;
	}

	public void setCheckpointId(String checkPointId) {
		this.checkpointId = checkPointId;
	}

	public boolean isCheckin() {
		return checkin;
	}

	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
