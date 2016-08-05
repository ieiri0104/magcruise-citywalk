package org.magcruise.citywalk.jsonrpc.impl;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.CityWalkContentReader;
import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.json.CheckpointJson;
import org.magcruise.citywalk.model.json.InitialDataJson;
import org.magcruise.citywalk.model.json.RewardJson;
import org.magcruise.citywalk.model.relation.ActivitiesTable;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.relation.UserAccountsTable;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
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
	public RewardJson addActivity(ActivityJson activityJson) {
		EventManager.offerEvent(activityJson.getUserId(), activityJson);
		activities.insert(new Activity(activityJson));
		return new RewardJson();
	}

	@Override
	public ActivityJson[] getActivities(String userId) {
		return Arrays.stream(activities.getActivities(userId)).map(a -> new ActivityJson(a))
				.collect(Collectors.toList()).toArray(new ActivityJson[0]);
	}

	@Override
	public CheckpointJson getCheckpoint(String checkPointId) {
		return new CheckpointJson(checkpoints.readByPrimaryKey(checkPointId));

	}

	@Override
	public CheckpointJson[] getCheckpoints(String checkPointGroupId) {
		Checkpoint[] results = checkpoints.getCheckpoints(checkPointGroupId);
		return Arrays.stream(results).map(c -> new CheckpointJson(c)).collect(Collectors.toList())
				.toArray(new CheckpointJson[0]);
	}

	@Override
	public ActivityJson[] getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return activities.getNewActivitiesOrderById(userId, latestActivityId).stream()
				.map(a -> new ActivityJson(a)).collect(Collectors.toList())
				.toArray(new ActivityJson[0]);
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
