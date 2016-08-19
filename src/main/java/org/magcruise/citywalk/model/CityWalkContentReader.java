package org.magcruise.citywalk.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.content.TaskContent;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class CityWalkContentReader {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args)
			throws JSONException, FileNotFoundException, IOException {
		Map<String, Object> data = JSON
				.decode(new FileReader(new File("data/initial_data.json")));
		new CityWalkContentReader().merge(data);
	}

	public boolean merge(Map<String, Object> data) {
		try {
			new CheckpointsTable().mergeBatch(
					readCheckpoints(data).toArray(new Checkpoint[0]));
			new TasksTable().mergeBatch(readTasks(data).toArray(new Task[0]));
			return true;
		} catch (RuntimeException e) {
			throw e;
		}

	}

	public boolean validate(String json) {
		Map<String, Object> data = JSON.decode(json);
		return validate(data);

	}

	public boolean validate(Map<String, Object> data) {
		try {
			readCheckpoints(data);
			readTasks(data);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	private List<Checkpoint> readCheckpoints(Map<String, Object> data) {
		List<Map<String, Object>> checkpointsData = (List<Map<String, Object>>) data
				.get("checkpoints");
		List<Checkpoint> checkpoints = checkpointsData.stream()
				.map(checkpoint -> {
					String id = checkpoint.get("id").toString();
					String name = checkpoint.get("name").toString();
					double lat = ((Number) checkpoint.get("lat")).doubleValue();
					double lon = ((Number) checkpoint.get("lat")).doubleValue();
					List<String> checkpointGroupIds = (List<String>) checkpoint
							.get("checkpoint_group_ids");
					return new Checkpoint(id, name, lat, lon, checkpointGroupIds);
				}).collect(Collectors.toList());
		return checkpoints;
	}

	@SuppressWarnings("unchecked")
	private List<Task> readTasks(Map<String, Object> data) {
		List<Map<String, Object>> tasksData = (List<Map<String, Object>>) data
				.get("tasks");
		List<Task> tasks = tasksData.stream().map(task -> {
			Map<String, Object> contentData = ((Map<String, Object>) task
					.get("content"));
			List<String> checkpointIds = (List<String>) task
					.get("checkpoint_ids");

			try {
				TaskContent content = JsonConstructiveObject.decodeFromJson(
						(Class<? extends TaskContent>) Class.forName(
								(String) contentData.get("instanceClass")),
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
