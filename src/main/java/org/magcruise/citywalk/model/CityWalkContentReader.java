package org.magcruise.citywalk.model;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.content.TaskContent;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;

import net.arnx.jsonic.JSON;

public class CityWalkContentReader {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Map<String, Object> data = JSON
					.decode(new FileReader(new File("data.json")));
			log.debug(data);

			List<Map<String, Object>> checkpointsData = (List<Map<String, Object>>) data
					.get("checkpoints");

			List<Checkpoint> checkpoints = checkpointsData.stream()
					.map(checkpoint -> {
						String id = checkpoint.get("id").toString();
						double lat = ((Number) checkpoint.get("lat"))
								.doubleValue();
						double lon = ((Number) checkpoint.get("lat"))
								.doubleValue();
						List<String> checkpointGroupIds = (List<String>) checkpoint
								.get("checkpoint_group_ids");
						return new Checkpoint(id, lat, lon, checkpointGroupIds);
					}).collect(Collectors.toList());
			log.debug(checkpoints);
			new CheckpointsTable()
					.mergeBatch(checkpoints.toArray(new Checkpoint[0]));

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
			log.debug(tasks);

			new TasksTable().mergeBatch(tasks.toArray(new Task[0]));

		} catch (Exception e) {
			log.error(e, e);
		}
	}

}
