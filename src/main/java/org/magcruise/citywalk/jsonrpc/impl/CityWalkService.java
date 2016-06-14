package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.table.Activities;
import org.magcruise.citywalk.model.table.Checkpoints;
import org.magcruise.citywalk.model.table.Tasks;
import org.magcruise.citywalk.model.table.Users;

public class CityWalkService extends AbstractCityWalkService
		implements CityWalkServiceInterface {

	private Activities activities = new Activities();
	private Users users = new Users();
	private Tasks tasks = new Tasks();
	private Checkpoints checkpoints = new Checkpoints();

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
		activities.insert(activity);
		log.debug(activity.getInput());
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
