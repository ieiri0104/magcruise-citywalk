package org.magcruise.citywalk.model.table;

import java.util.List;

import org.magcruise.citywalk.model.row.Activity;

public class Activities extends TableModel<Activity> {

	@Override
	protected String getTableSchema() {
		return getTableName() + "(id bigint primary key auto_increment, "
				+ "user_id varchar, " + "task_id varchar, " + "score double,"
				+ "input varchar" + ")";
	}

	public List<Activity> getActivities(String userId) {
		return getClient().readList(Activity.class,
				"SELECT * FROM " + getTableName() + " WHERE user_id=?", userId);
	}

	public List<Activity> getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return getClient().readList(Activity.class,
				"SELECT * FROM " + getTableName()
						+ " WHERE user_id=? AND id>? ORDER BY id",
				userId, latestActivityId);
	}



}
