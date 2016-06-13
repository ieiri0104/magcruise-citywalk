package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.Activity;
import org.magcruise.citywalk.model.Checkpoint;
import org.magcruise.citywalk.model.Input;
import org.magcruise.citywalk.model.Task;

public class CityWalkService extends AbstractCityWalkService
		implements CityWalkServiceInterface {

	private ActivityService activityService = new ActivityService();
	private UserService userService = new UserService();
	private CheckpointService checkpointService = new CheckpointService();

	@Override
	public void testException() {
		throw new RuntimeException("ERROR");
	}

	@Override
	public void addActivity(String userId, String taskId, int score,
			Input inputs) {
		activityService.addActivity(userId, taskId, score, inputs);
	}

	@Override
	public void addActivity(Activity activity) {
		activityService.addActivity(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return activityService.getActivities(userId);
	}

	@Override
	public void login(String userId, String groupId) {
		userService.login(userId, groupId);
	}

	@Override
	public Checkpoint getCheckpoint(String id) {
		return checkpointService.getCheckpoint(id);
	}

	@Override
	public List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId) {
		return activityService.getNewActivitiesOrderById(userId, latestActivityId);
	}

	@Override
	public List<Task> getTasks(String checkpointId) {
		return null;
	}

}
