package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.ActivityServiceInterface;
import org.magcruise.citywalk.model.Activity;
import org.magcruise.citywalk.model.Input;

public class ActivityService extends AbstractCityWalkService
		implements ActivityServiceInterface {

	@Override
	public void addActivity(String userId, String activityId, int score,
			Input inputs) {
		log.debug("{},{},{}, {}", userId, activityId, score, inputs);
		getClient().insert(new Activity(userId, activityId, score, inputs));
	}

	@Override
	public void addActivity(Activity activity) {
		getClient().insert(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return getClient().readList(Activity.class, "SELECT * FROM "
				+ new Activity().getTableName() + " WHERE user_id=?", userId);
	}



	@Override
	public List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId) {
		return getClient().readList(Activity.class,
				"SELECT * FROM " + new Activity().getTableName()
						+ " WHERE user_id=? AND id>? ORDER BY id",
				userId, latestActivityId);
	}
}
