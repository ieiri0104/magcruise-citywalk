package org.magcruise.citywalk.model.conv;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.common.JsonConstructiveObject;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.task.TaskContent;
import org.nkjmlab.util.io.FileUtils;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class CheckpointsAndTasksFactory {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		log.info(refreshAndInsertToDb("src/main/webapp/json/checkpoints-and-tasks/waseda.json"));
	}

	public static Map<String, Object> refreshAndInsertToDb(String file) {
		try {
			Map<String, Object> data = JSON.decode(FileUtils.getFileReader(file));
			log.info("mergeToDb:{}", data);
			refreshAndInsertToDb(data);
			return data;
		} catch (JSONException | IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void refreshAndInsertToDb(Map<String, Object> data) {
		new TasksTable().dropTableIfExists();
		new CheckpointsTable().dropTableIfExists();
		new TasksTable().createTableIfNotExists();
		new CheckpointsTable().createTableIfNotExists();
		new CheckpointsTable().insertBatch(createCheckpoints(data).toArray(new Checkpoint[0]));
		new TasksTable().insertBatch(createTasks(data).toArray(new Task[0]));

	}

	public static boolean validate(String json) {
		Map<String, Object> data = JSON.decode(json);
		return validate(data);

	}

	public static boolean validate(Map<String, Object> data) {
		try {
			createCheckpoints(data);
			createTasks(data);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Checkpoint> createCheckpoints(Map<String, Object> data) {
		List<Map<String, Object>> checkpointsData = (List<Map<String, Object>>) data
				.get("checkpoints");
		List<Checkpoint> checkpoints = checkpointsData.stream()
				.map(checkpoint -> {
					String id = checkpoint.get("id").toString();
					String name = checkpoint.get("name").toString();
					String label = checkpoint.get("label").toString();
					double lat = ((Number) checkpoint.get("lat")).doubleValue();
					double lon = ((Number) checkpoint.get("lon")).doubleValue();
					List<String> checkpointGroupIds = (List<String>) checkpoint
							.get("checkpoint_group_ids");
					return new Checkpoint(id, name, label, lat, lon, checkpointGroupIds);
				}).collect(Collectors.toList());
		return checkpoints;
	}

	@SuppressWarnings("unchecked")
	public static List<Task> createTasks(Map<String, Object> data) {
		List<Map<String, Object>> tasksData = (List<Map<String, Object>>) data
				.get("tasks");
		List<Task> tasks = tasksData.stream().map(task -> {
			Map<String, Object> contentData = ((Map<String, Object>) task
					.get("content"));
			List<String> checkpointIds = (List<String>) task.get("checkpoint_ids");
			String id = (String) task.get("id");
			try {
				TaskContent content = JsonConstructiveObject.decodeFromJson(
						(Class<? extends TaskContent>) Class
								.forName((String) contentData.get("instanceClass")),
						JSON.encode(contentData));
				return new Task(id, checkpointIds, content);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		return tasks;

	}

}
