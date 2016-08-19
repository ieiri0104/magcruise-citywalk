package org.magcruise.citywalk.model.json;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.row.Activity;

public class ActivityJson {

	private long id;
	private String userId;

	private String checkpointId;
	/** checkinでないときsize=0の文字列 **/
	private String checkinType = "";

	private double lat;
	private double lon;

	/** taskでないときsize=0の文字列 **/
	private String taskType = "";
	private long taskId;

	private double score;

	/** key-value. valueはjson化された文字列を想定． **/
	private Map<String, String> inputs;

	private Date saved = new Timestamp(new Date().getTime());

	public ActivityJson() {
	}

	public ActivityJson(Activity activity) {
		this.id = activity.getId();
		this.userId = activity.getUserId();
		this.checkpointId = activity.getCheckpointId();
		this.lat = activity.getLat();
		this.lon = activity.getLon();

		this.taskId = activity.getTaskId();
	}

	public ActivityJson(String userId, long taskId, Map<String, String> inputs) {
		this.userId = userId;
		this.taskId = taskId;
		this.inputs = inputs;
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

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Map<String, String> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, String> inputs) {
		this.inputs = inputs;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getCheckinType() {
		return checkinType;
	}

	public void setCheckinType(String checkinType) {
		this.checkinType = checkinType;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCheckpointId() {
		return checkpointId;
	}

	public void setCheckpointId(String checkPointId) {
		this.checkpointId = checkPointId;
	}

}
