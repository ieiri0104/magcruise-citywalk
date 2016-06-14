package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.content.Input;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;

public class CityWalkService extends AbstractCityWalkService
		implements CityWalkServiceInterface {

	private ActivityService activityService = new ActivityService();
	private UserService userService = new UserService();
	private CheckpointService checkpointService = new CheckpointService();
	private TaskService taskService = new TaskService();

	@Override
	public void testException() {
		throw new RuntimeException("ERROR");
	}

	@Override
	public void addActivity(String userId, long taskId, double score,
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
			long latestActivityId) {
		return activityService.getNewActivitiesOrderById(userId,
				latestActivityId);
	}

	@Override
	public List<Task> getTasks(String checkpointId) {
		return taskService.getTasks(checkpointId);
	}

}
