package org.magcruise.citywalk.model.row;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.JsonConstructiveObject;
import org.magcruise.citywalk.model.content.Input;
import org.magcruise.citywalk.model.content.SelectionInput;
import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.relation.ActivitiesTable;

import net.sf.persist.annotations.Column;
import net.sf.persist.annotations.NoColumn;
import net.sf.persist.annotations.Table;

@Table(name = ActivitiesTable.TABLE_NAME)
public class Activity extends RowModel<Activity> {

	private long id;
	private String userId;
	private long taskId;

	private double lat;
	private double lon;

	private double score;
	private Input input;
	private Date saved = new Timestamp(new Date().getTime());

	public Activity() {
	}

	public Activity(ActivityJson activityJson) {
		this(new Timestamp(new Date().getTime()), activityJson.getUserId(),
				activityJson.getTaskId(), activityJson.getScore(),
				convertToInput(activityJson.getInputs()));
	}

	private static Input convertToInput(Map<String, String> inputs) {
		// TODO stub
		return new SelectionInput();
	}

	public Activity(String userId, long taskId, double score, Input input) {
		this(new Timestamp(new Date().getTime()), userId, taskId, score, input);
	}

	public Activity(Date saved, String userId, long taskId, double score, Input input) {
		this.saved = saved;
		this.userId = userId;
		this.taskId = taskId;
		this.score = score;
		setInputObject(input);
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Column(autoGenerated = true)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInput() {
		return input.encodeToJson();
	}

	public void setInput(String json) {
		this.input = JsonConstructiveObject.decodeFromJson(Input.class, json);
	}

	@NoColumn
	public Input getInputObject() {
		return input;
	}

	/**
	 * InputはJSON-RPCライブラリでJson変換可能なのでOK．
	 *
	 * @param input
	 *
	 */
	public void setInputObject(Input input) {
		this.input = input;
	}

	public String getCheckpointId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

}
