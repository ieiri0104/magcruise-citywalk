package org.magcruise.citywalk.model.row;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.magcruise.citywalk.model.JsonConstructiveObject;
import org.magcruise.citywalk.model.content.Input;

import net.sf.persist.annotations.Column;
import net.sf.persist.annotations.NoColumn;
import net.sf.persist.annotations.Table;

@Table(name = "ACTIVITIES")
public class Activity extends RowModel<Activity> {

	private long id;
	private String userId;
	private long taskId;
	private double score;
	private Input input;

	public Activity() {
	}

	public Activity(String userId, long taskId, double score, Input input) {
		this.userId = userId;
		this.taskId = taskId;
		this.score = score;
		setInputObject(input);
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
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
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
	 * InputはJson変換可能なのでOK．
	 *
	 * @param input
	 *
	 */
	public void setInputObject(Input input) {
		this.input = input;
	}

}
