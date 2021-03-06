package org.magcruise.citywalk.model.conv;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.common.JsonConstructiveObject;
import org.magcruise.citywalk.model.json.db.CheckpointJson;
import org.magcruise.citywalk.model.json.db.CheckpointsAndTasksJson;
import org.magcruise.citywalk.model.json.db.TaskJson;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.task.TaskContent;
import org.nkjmlab.util.io.FileUtils;
import org.nkjmlab.util.json.JsonUtils;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

public class CheckpointsAndTasksFactory {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		CheckpointsAndTasksJson json = JsonUtils.decode(
				"src/main/webapp/json/checkpoints-and-tasks/waseda.json",
				CheckpointsAndTasksJson.class);
		log.info(createCheckpoints(json.getCheckpoints()));
		log.info(createTasks(json.getTasks()));
		log.info(refreshAndInsertToDb("src/main/webapp/json/checkpoints-and-tasks/waseda.json"));
	}

	public static CheckpointsAndTasksJson refreshAndInsertToDb(String file) {
		try {
			CheckpointsAndTasksJson json = JSON.decode(FileUtils.getFileReader(file),
					CheckpointsAndTasksJson.class);
			log.info("mergeToDb:{}", json);
			refreshAndInsertToDb(json);
			return json;
		} catch (JSONException | IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void refreshAndInsertToDb(CheckpointsAndTasksJson json) {
		new TasksTable().dropTableIfExists();
		new CheckpointsTable().dropTableIfExists();
		new TasksTable().createTableIfNotExists();
		new CheckpointsTable().createTableIfNotExists();
		new CheckpointsTable()
				.insertBatch(createCheckpoints(json.getCheckpoints()).toArray(new Checkpoint[0]));
		new TasksTable().insertBatch(createTasks(json.getTasks()).toArray(new Task[0]));

	}

	public static boolean validate(String json) {
		JSON.decode(json, CheckpointsAndTasksJson.class);
		return true;

	}

	public static List<Checkpoint> createCheckpoints(List<CheckpointJson> checkpointsData) {
		List<Checkpoint> checkpoints = checkpointsData.stream()
				.map(checkpoint -> new Checkpoint(checkpoint.getId(), checkpoint.getName(),
						checkpoint.getLabel(), checkpoint.getLat(), checkpoint.getLon(),
						checkpoint.getCheckpointGroupIds()))
				.collect(Collectors.toList());
		return checkpoints;
	}

	public static List<Task> createTasks(List<TaskJson> json) {
		List<Task> tasks = json.stream().map(task -> {
			try {
				@SuppressWarnings("unchecked")
				TaskContent content = JsonConstructiveObject.decodeFromJson(
						(Class<? extends TaskContent>) Class
								.forName(task.getContent().getInstanceClass()),
						JSON.encode(task.getContent()));
				return new Task(task.getId(), task.getCheckpointIds(), content);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		return tasks;

	}

}
