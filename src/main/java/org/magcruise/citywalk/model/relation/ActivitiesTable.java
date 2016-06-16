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
		return TABLE_NAME + "(" + ID + " bigint primary key auto_increment, "
				+ CREATED + " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + SAVED
				+ " TIMESTAMP, " + INSTANCE_CLASS + " varchar, " + USER_ID
				+ " varchar, " + "task_id varchar, " + "score double,"
				+ "input varchar" + ")";
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
