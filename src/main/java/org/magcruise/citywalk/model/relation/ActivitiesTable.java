package org.magcruise.citywalk.model.relation;

import java.util.List;

import org.magcruise.citywalk.model.row.Activity;

public class ActivitiesTable extends RelationalModel<Activity> {

	public static final String TABLE_NAME = "ACTIVITIES";

	@Override
	protected String getRelationName() {
		return TABLE_NAME;
	}

	@Override
	protected String getRelationalSchema() {
		return TABLE_NAME + "(" + ID + " BIGINT PRIMARY KEY AUTO_INCREMENT, "
				+ CREATED + " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + SAVED
				+ " TIMESTAMP, " + INSTANCE_CLASS + " VARCHAR, " + USER_ID
				+ " VARCHAR, " + "checkpoint_id VARCHAR, "+"task_id VARCHAR, " + "score DOUBLE," + "lat DOUBLE,"
				+ "lon DOUBLE," + "input VARCHAR" + ")";
	}

	public Activity[] getActivities(String userId) {
		return getClient()
				.readList(Activity.class,
						"SELECT * FROM " + getRelationName() + " WHERE "
								+ USER_ID + "=?",
						userId)
				.toArray(new Activity[0]);

	}

	public List<Activity> getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return getClient().readList(
				Activity.class, "SELECT * FROM " + TABLE_NAME + " WHERE "
						+ USER_ID + "=? AND " + ID + ">? ORDER BY " + ID,
				userId, latestActivityId);
	}

}
