package org.magcruise.citywalk.jsonrpc.impl;

import java.io.File;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.CityWalkContentReader;
import org.magcruise.citywalk.model.json.InitialDataJson;
import org.magcruise.citywalk.model.json.RewardJson;
import org.magcruise.citywalk.model.relation.ActivitiesTable;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.relation.UserAccountsTable;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.row.User;
import org.magcruise.citywalk.websocket.EventManager;
import org.nkjmlab.util.base64.Base64ImageUtils;
import org.nkjmlab.util.io.FileUtils;
import org.nkjmlab.util.json.JsonUtils;

public class CityWalkService extends AbstractCityWalkService
		implements CityWalkServiceInterface {

	private ActivitiesTable activities = new ActivitiesTable();
	private UserAccountsTable users = new UserAccountsTable();
	private TasksTable tasks = new TasksTable();
	private CheckpointsTable checkpoints = new CheckpointsTable();

	@Override
	public boolean login(String userId, String groupId) {
		CityWalkSession session = getSession();
		users.merge(new User(userId, groupId));
		if (session.isLogined()) {
			log.debug("already logined as {}", session.getUserId());

			if (!session.getUserId().equals(userId)) {
				log.debug("userId is changed from {} to {}",
						session.getUserId(), userId);
				session.setUserId(userId);
			}
			if (!session.getGroupId().equals(groupId)) {
				log.debug("groupId is changed from {} to {}",
						session.getGroupId(), groupId);
				session.setGroupId(groupId);
			}

			EventManager.offerEvent(userId,
					userId + "@" + groupId + " is logined.");
			return true;
		} else {
			log.debug("create new session for {}", userId);
			session.setMaxInactiveInterval(10 * 60 * 60);
			session.setUserId(userId);
			session.setGroupId(groupId);
			EventManager.offerEvent(userId,
					userId + "@" + groupId + " is logined.");
			return true;
		}
	}

	@Override
	public RewardJson addActivity(Activity activity) {
		log.debug(activity);
		log.debug(activity.getInput());
		EventManager.offerEvent(activity.getUserId(), activity);
		activities.insert(activity);
		return new RewardJson();
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

	@Override
	public void uploadImage(String base64EncodedImage) {
		try {
			log.debug(base64EncodedImage);
			Base64ImageUtils.decodeAndWrite(base64EncodedImage, "jpg", FileUtils
					.getTempFile("citywalk-" + System.nanoTime() + ".jpg"));
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public InitialDataJson getInitialData(String checkpointGroupId) {
		InitialDataJson data = JsonUtils.decode(
				new File(getServiceContext().getRealPath("/json/initial_data.json")),
				InitialDataJson.class);

		return data;
	}

	@Override
	public boolean validateCheckpointsAndTasksJson(String json) {
		return new CityWalkContentReader().validate(json);
	}

}
