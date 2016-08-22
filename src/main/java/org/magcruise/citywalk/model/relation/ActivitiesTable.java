package org.magcruise.citywalk.model.relation;

import java.util.List;
import java.util.Map;

import org.magcruise.citywalk.model.row.Activity;

public abstract class ActivitiesTable extends RelationalModel<Activity> {

	protected String tableName = "";

	public ActivitiesTable() {
	}

	@Override
	protected String getRelationName() {
		return tableName;
	}

	@Override
	protected String getRelationalSchema() {
		return getRelationName() + "(" + ID + " BIGINT PRIMARY KEY AUTO_INCREMENT, " + CREATED
				+ " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + SAVED + " TIMESTAMP, " + INSTANCE_CLASS + " VARCHAR, "
				+ USER_ID + " VARCHAR, " + "checkpoint_id VARCHAR, " + "task_id VARCHAR, " + "score DOUBLE,"
				+ "lat DOUBLE," + "lon DOUBLE," + "input VARCHAR" + ")";
	}

	public List<Activity> getActivities(String userId) {
		return getClient().readList(Activity.class, "SELECT * FROM " + getRelationName() + " WHERE " + USER_ID + "=?",
				userId);

	}

	public List<Activity> getNewActivitiesOrderById(String userId, long latestActivityId) {
		return getClient().readList(Activity.class,
				"SELECT * FROM " + tableName + " WHERE " + USER_ID + "=? AND " + ID + ">? ORDER BY " + ID, userId,
				latestActivityId);
	}

	public List<Activity> getActivities(String userId, String checkpointId, String taskId) {
		return getClient().readList(Activity.class, "SELECT * FROM " + getRelationName() + " WHERE " + USER_ID
				+ "=? AND " + CHECKPOINT_ID + "=? AND " + TASK_ID + "=?", userId, checkpointId, taskId);

	}

	public List<Map<String, Object>> sumsOfScoreGroupByUserIdOrderByScore() {
		return getClient().readMapList("SELECT " + USER_ID + ", SUM(score) AS sum_of_score FROM " + getRelationName()
				+ " GROUP BY " + USER_ID + " ORDER BY sum_of_score");

	}

	public boolean contains(String userId, String checkpointId, String taskId) {
		return getActivities(userId, checkpointId, taskId).size() != 0;
	}

}
