package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.ActivityServiceInterface;
import org.magcruise.citywalk.model.Input;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.table.Activities;

public class ActivityService extends AbstractCityWalkService
		implements ActivityServiceInterface {

	private Activities activities = new Activities();

	@Override
	public void addActivity(String userId, long activityId, double score,
			Input inputs) {
		log.debug("{},{},{}, {}", userId, activityId, score, inputs);
		activities.insert(new Activity(userId, activityId, score, inputs));
	}

	@Override
	public void addActivity(Activity activity) {
		activities.insert(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return activities.readList(userId);
	}

	@Override
	public List<Activity> getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return activities.getNewActivitiesOrderById(userId, latestActivityId);
	}
}
