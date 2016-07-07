package org.magcruise.citywalk.jsonrpc.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.CityWalkContentReader;
import org.magcruise.citywalk.model.relation.ActivitiesTable;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.relation.UserAccountsTable;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.row.User;
import org.magcruise.citywalk.websocket.EventManager;

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
	public void addActivity(Activity activity) {
		log.debug(activity);
		log.debug(activity.getInput());
		EventManager.offerEvent(activity.getUserId(), activity);
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

	@Override
	public void uploadImage(String base64EncodedImage) {
		try {
			log.debug(base64EncodedImage);
			byte[] decoded = Base64.getDecoder().decode(
					base64EncodedImage.replaceFirst("data:.*?base64,", ""));
			BufferedImage image = ImageIO
					.read(new ByteArrayInputStream(decoded));
			log.debug(image);
			ImageIO.write(image, "jpg",
					new File(System.getProperty("java.io.tmpdir"),
							"citywalk-" + System.nanoTime() + ".jpg"));
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public Map<String, Object> getInitialData(String checkpointGroupId) {
		Map<String, Object> result = new HashMap<>();
		result.put("checkpoints",
				checkpoints.getCheckpoints(checkpointGroupId));
		result.put("tasks",
				tasks.getTasksForCheckpointGroup(checkpointGroupId));
		return result;
	}

	@Override
	public boolean validateCheckpointsAndTasksJson(String json) {
		return new CityWalkContentReader().validate(json);
	}

}
