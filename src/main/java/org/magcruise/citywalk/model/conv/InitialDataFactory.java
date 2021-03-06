package org.magcruise.citywalk.model.conv;

import java.util.List;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.json.init.CheckinJson;
import org.magcruise.citywalk.model.json.init.CheckpointJson;
import org.magcruise.citywalk.model.json.init.InitialDataJson;
import org.magcruise.citywalk.model.json.init.TaskJson;
import org.magcruise.citywalk.model.relation.CheckpointsTable;
import org.magcruise.citywalk.model.relation.TasksTable;
import org.magcruise.citywalk.model.row.Task;
import org.nkjmlab.util.json.JsonUtils;

public class InitialDataFactory {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	public static void main(String[] args) {
		CheckpointsAndTasksFactory
				.refreshAndInsertToDb("src/main/webapp/json/checkpoints-and-tasks/waseda.json");
		JsonUtils.encode(create("waseda"),
				"src/main/webapp/json/initial-data/waseda.json", true);
	}

	public static InitialDataJson create(String checkpointGroupId) {
		List<CheckpointJson> result = new CheckpointsTable()
				.findByCheckpointGroupId(checkpointGroupId)
				.stream()
				.map(c -> {
					List<Task> tasks = new TasksTable().getTasks(c.getId());
					TaskJson task = new TaskJson();
					CheckinJson checkin = new CheckinJson();
					for (Task t : tasks) {
						if (t.getContentObject().isCheckin()) {
							checkin = new CheckinJson(t);
						} else {
							task = new TaskJson(t);
						}
					}
					return new CheckpointJson(c.getId(), c.getName(), c.getLabel(), c.getLat(),
							c.getLon(), checkin, task);
				}).collect(Collectors.toList());
		return new InitialDataJson(result);
	}
}
