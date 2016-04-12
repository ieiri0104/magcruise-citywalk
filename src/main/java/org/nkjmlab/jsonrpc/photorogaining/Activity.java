package org.nkjmlab.jsonrpc.photorogaining;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * JSONで受け渡しをするクラスはPOJOでなくてはならない．
 *
 * @author nkjm
 *
 */
public class Activity {

	private String userId;
	private String taskId;
	private int score;

	public Activity() {
	}

	public Activity(String userId, String taskId, int score) {
		this.userId = userId;
		this.taskId = taskId;
		this.score = score;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
