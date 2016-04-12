package org.nkjmlab.jsonrpc.photorogaining;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class PhotoRogainingService implements PhotoRogainingServiceInterface {
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private static volatile Map<String, List<Activity>> activities = new ConcurrentHashMap<>();

	@Override
	public void insertActivity(String userId, String taskId, int score) {
		log.debug("{},{},{}", userId, taskId, score);
		getActivitiesList(userId).add(new Activity(userId, taskId, score));
	}

	@Override
	public void addActivity(Activity activity) {
		getActivitiesList(activity.getUserId()).add(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return getActivitiesList(userId);
	}

	private List<Activity> getActivitiesList(String userId) {
		activities.putIfAbsent(userId, new CopyOnWriteArrayList<>());
		return activities.get(userId);
	}

	@Override
	public void testException() {
		throw new RuntimeException("ERROR");
	}

}
