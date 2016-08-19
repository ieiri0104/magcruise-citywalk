package org.magcruise.citywalk.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.task.TaskContent;
import org.nkjmlab.util.io.FileUtils;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class CheckpointsAndTasksImporter {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		log.info(importAndMerge("src/main/webapp/json/checkpoints_and_tasks.json"));

	}

	public static Map<String, Object> importAndMerge(String file) {
		try {
			new TasksTable().createTableIfNotExists();
			new CheckpointsTable().createTableIfNotExists();
			Map<String, Object> data = JSON.decode(FileUtils.getFileReader(file));
			merge(data);
			return data;
		} catch (JSONException | IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static boolean merge(Map<String, Object> data) {
		try {
			new CheckpointsTable().mergeBatch(readCheckpoints(data).toArray(new Checkpoint[0]));
			new TasksTable().mergeBatch(readTasks(data).toArray(new Task[0]));
			return true;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	public static boolean validate(String json) {
		Map<String, Object> data = JSON.decode(json);
		return validate(data);

	}

	public static boolean validate(Map<String, Object> data) {
		try {
			readCheckpoints(data);
			readTasks(data);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	private static List<Checkpoint> readCheckpoints(Map<String, Object> data) {
		List<Map<String, Object>> checkpointsData = (List<Map<String, Object>>) data
				.get("checkpoints");
		List<Checkpoint> checkpoints = checkpointsData.stream()
				.map(checkpoint -> {
					String id = checkpoint.get("id").toString();
					String name = checkpoint.get("name").toString();
					double lat = ((Number) checkpoint.get("lat")).doubleValue();
					double lon = ((Number) checkpoint.get("lon")).doubleValue();
					List<String> checkpointGroupIds = (List<String>) checkpoint
							.get("checkpoint_group_ids");
					return new Checkpoint(id, name, lat, lon, checkpointGroupIds);
				}).collect(Collectors.toList());
		return checkpoints;
	}

	@SuppressWarnings("unchecked")
	private static List<Task> readTasks(Map<String, Object> data) {
		List<Map<String, Object>> tasksData = (List<Map<String, Object>>) data
				.get("tasks");
		List<Task> tasks = tasksData.stream().map(task -> {
			Map<String, Object> contentData = ((Map<String, Object>) task
					.get("content"));
			List<String> checkpointIds = (List<String>) task
					.get("checkpoint_ids");

			try {
				TaskContent content = JsonConstructiveObject.decodeFromJson(
						(Class<? extends TaskContent>) Class
								.forName((String) contentData.get("instanceClass")),
						JSON.encode(contentData));
				return new Task(checkpointIds, content);
			} catch (ClassNotFoundException e) {
				log.error(e, e);
				return null;
			}
		}).collect(Collectors.toList());
		return tasks;

	}

}
