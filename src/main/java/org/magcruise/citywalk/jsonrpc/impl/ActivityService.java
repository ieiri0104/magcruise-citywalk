package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.ActivityServiceInterface;
import org.magcruise.citywalk.model.Activity;
import org.magcruise.citywalk.model.Input;

public class ActivityService extends AbstractCityWalkService
		implements ActivityServiceInterface {

	static {
		client.dropTableIfExists(Activity.TABLE_NAME);
		client.createTableIfNotExists(Activity.TABLE_NAME
				+ "(id int primary key auto_increment, user_id varchar, task_id varchar, score int)");
	}

	@Override
	public void addActivity(String userId, String activityId, int score,
			Input inputs) {
		log.debug("{},{},{}, {}", userId, activityId, score, inputs);
		client.insert(new Activity(userId, activityId, score, inputs));
	}

	@Override
	public void addActivity(Activity activity) {
		client.insert(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return client.readList(Activity.class,
				"SELECT * FROM " + Activity.TABLE_NAME + " WHERE user_id=?",
				userId);
	}

	@Override
	public List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId) {
		return client.readList(Activity.class,
				"SELECT * FROM " + Activity.TABLE_NAME
						+ " WHERE user_id=? AND id>? ORDER BY id",
				userId, latestActivityId);
	}
}
