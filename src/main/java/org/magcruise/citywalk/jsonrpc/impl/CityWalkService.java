package org.magcruise.citywalk.jsonrpc.impl;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.conv.CheckpointsAndTasksFactory;
import org.magcruise.citywalk.model.conv.InitialDataFactory;
import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.json.ActivityLogJson;
import org.magcruise.citywalk.model.json.RewardJson;
import org.magcruise.citywalk.model.json.init.InitialDataJson;
import org.magcruise.citywalk.model.relation.ActivitiesTable;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.relation.UserAccountsTable;
import org.magcruise.citywalk.model.row.Activity;
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
				log.debug("userId is changed from {} to {}", session.getUserId(), userId);
				session.setUserId(userId);
			}
			if (!session.getGroupId().equals(groupId)) {
				log.debug("groupId is changed from {} to {}", session.getGroupId(), groupId);
				session.setGroupId(groupId);
			}

			EventManager.offerEvent(userId, userId + "@" + groupId + " is logined.");
			return true;
		} else {
			log.debug("create new session for {}", userId);
			session.setMaxInactiveInterval(10 * 60 * 60);
			session.setUserId(userId);
			session.setGroupId(groupId);
			EventManager.offerEvent(userId, userId + "@" + groupId + " is logined.");
			return true;
		}
	}

	@Override
	public RewardJson addActivity(ActivityJson json) {
		EventManager.offerEvent(json.getUserId(), json);
		activities.insert(new Activity(json));
		return new RewardJson();
	}

	@Override
	public ActivityLogJson[] getActivityLogs(String userId) {
		return Arrays.stream(activities.getActivities(userId))
				.map(a -> new ActivityLogJson(a, tasks.isCheckin(a.getTaskId())))
				.collect(Collectors.toList()).toArray(new ActivityLogJson[0]);
	}

	@Override
	public ActivityLogJson[] getNewActivityLogsOrderById(String userId,
			long latestActivityId) {
		return activities.getNewActivitiesOrderById(userId, latestActivityId).stream()
				.map(a -> new ActivityLogJson(a, tasks.isCheckin(a.getTaskId())))
				.collect(Collectors.toList())
				.toArray(new ActivityLogJson[0]);
	}

	@Override
	public String uploadImage(String userId, String base64EncodedImage) {
		try {
			log.debug(base64EncodedImage);
			String imageId = "citywalk-" + userId + "-" + System.nanoTime();
			Base64ImageUtils.decodeAndWrite(base64EncodedImage, "jpg",
					FileUtils.getTempFile(imageId + ".jpg"));
			return imageId;
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public InitialDataJson getInitialData(String checkpointGroupId) {
		return InitialDataFactory.create(checkpointGroupId);
	}

	@Override
	public InitialDataJson getInitialDataFromFile(String checkpointGroupId) {
		InitialDataJson data = JsonUtils.decode(
				new File(getServiceContext()
						.getRealPath("json/initial-data/" + checkpointGroupId + ".json")),
				InitialDataJson.class);
		return data;
	}

	@Override
	public boolean validateCheckpointsAndTasksJson(String json) {
		return CheckpointsAndTasksFactory.validate(json);
	}

}
