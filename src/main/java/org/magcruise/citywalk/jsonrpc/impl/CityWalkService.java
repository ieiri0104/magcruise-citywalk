package org.magcruise.citywalk.jsonrpc.impl;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.table.ActivitiesTable;
import org.magcruise.citywalk.model.table.CheckpointsTable;
import org.magcruise.citywalk.model.table.TasksTable;
import org.magcruise.citywalk.model.table.UserAccountsTable;

public class CityWalkService extends AbstractCityWalkService
		implements CityWalkServiceInterface {

	private ActivitiesTable activities = new ActivitiesTable();
	private UserAccountsTable users = new UserAccountsTable();
	private TasksTable tasks = new TasksTable();
	private CheckpointsTable checkpoints = new CheckpointsTable();

	@Override
	public boolean login(String userId, String groupId) {
		CityWalkSession session = getSession();
		if (session.isLogined()) {
			log.debug(session.getId());
			log.debug("already logined as {}", session.getAttribute("userId"));
			return true;
		} else {
			log.debug("create new session for {}", userId);
			session.setMaxInactiveInterval(10 * 60 * 60);
			session.setAttribute("userId", userId);
			return true;
		}
	}

	@Override
	public void addActivity(Activity activity) {
		log.debug(activity);
		log.debug(activity.getInput());
		activities.insert(activity);
	}

	@Override
	public Activity[] getActivities(String userId) {
		return activities.getActivities(userId);
	}

	@Override
	public Checkpoint getCheckpoint(String checkPointId) {
		return checkpoints.readByPrimaryKey(checkPointId);
	}

	@Override
	public Checkpoint[] getCheckpoints(String checkPointGroupId) {
		Checkpoint[] result = checkpoints.getCheckpoints(checkPointGroupId);
		return result;
	}

	@Override
	public Activity[] getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return activities.getNewActivitiesOrderById(userId, latestActivityId)
				.toArray(new Activity[0]);
	}

	@Override
	public Task[] getTasks(String checkpointId) {
		return tasks.getTasks(checkpointId);
	}

}
