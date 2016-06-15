package org.magcruise.citywalk.model.table;

import java.util.List;

import org.magcruise.citywalk.model.row.Activity;

public class ActivitiesTable extends TableModel<Activity> {

	@Override
	protected String getTableSchema() {
		return getTableName() + "(" + ID + " bigint primary key auto_increment, "
				+ INSTANCE_CLASS + " varchar, " + USER_ID + " varchar, "
				+ "task_id varchar, " + "score double," + "input varchar" + ")";
	}

	public List<Activity> getActivities(String userId) {
		return getClient().readList(Activity.class,
				"SELECT * FROM " + getTableName() + " WHERE " + USER_ID + "=?",
				userId);
	}

	public List<Activity> getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return getClient().readList(Activity.class,
				"SELECT * FROM " + getTableName() + " WHERE " + USER_ID
						+ "=? AND " + ID + ">? ORDER BY " + ID,
				userId, latestActivityId);
	}

}
