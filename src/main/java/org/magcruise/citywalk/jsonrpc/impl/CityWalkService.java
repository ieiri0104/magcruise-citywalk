package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.table.ActivitiesTable;
import org.magcruise.citywalk.model.table.CheckpointsTable;
import org.magcruise.citywalk.model.table.TasksTable;
import org.magcruise.citywalk.model.table.UsersTable;

public class CityWalkService extends AbstractCityWalkService
		implements CityWalkServiceInterface {

	private ActivitiesTable activities = new ActivitiesTable();
	private UsersTable users = new UsersTable();
	private TasksTable tasks = new TasksTable();
	private CheckpointsTable checkpoints = new CheckpointsTable();

	@Override
	public void login(String userId, String groupId) {
		CityWalkSession session = getSession();
		if (session.isLogined()) {
			log.debug(session.getId());
			log.debug("already logined as {}", session.getAttribute("userId"));
			session.invalidate();
			log.debug("session will be invalidate.");
		} else {
			log.debug("create new session for {}", userId);
			session.setMaxInactiveInterval(10 * 60 * 60);
			session.setAttribute("userId", userId);
		}
	}

	@Override
	public void addActivity(Activity activity) {
		log.debug(activity);
		log.debug(activity.getInput());
		activities.insert(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return activities.getActivities(userId);
	}

	@Override
	public Checkpoint getCheckpoint(String checkPointId) {
		return checkpoints.readByPrimaryKey(checkPointId);
	}

	@Override
	public List<Checkpoint> getCheckpoints(String checkPointGroupId) {
		return checkpoints.getCheckpoints(checkPointGroupId);
	}

	@Override
	public List<Activity> getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return activities.getNewActivitiesOrderById(userId, latestActivityId);
	}

	@Override
	public List<Task> getTasks(String checkpointId) {
		return tasks.getTasks(checkpointId);
	}

}
