package org.magcruise.citywalk.model.relation;

import java.util.List;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.task.TaskContent;

public class TasksTable extends RelationalModel<Task> {

	public static final String TABLE_NAME = "TASKS";

	@Override
	protected String getRelationName() {
		return TABLE_NAME;
	}

	@Override
	protected String getRelationalSchema() {
		return TABLE_NAME + "(" + ID + " VARCHAR PRIMARY KEY , "
				+ CREATED + " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, "
				+ INSTANCE_CLASS + " VARCHAR, " + CHECKPOINT_IDS + " VARCHAR, "
				+ CONTENT + " VARCHAR)";
	}

	public List<Task> getTasks(String checkpointId) {
		return getClient()
				.readList(Task.class,
						"SELECT * FROM " + TABLE_NAME)
				.stream().filter(c -> c.getCheckpointIds().contains(checkpointId))
				.collect(Collectors.toList());
	}

	public List<Task> getTasksForCheckpointGroup(String checkpointGroupId) {
		List<Task> tasks = getClient().readList(Task.class,
				"SELECT " + TABLE_NAME + ".* FROM " + TABLE_NAME + " JOIN "
						+ CheckpointsTable.TABLE_NAME + " ON " + TABLE_NAME
						+ "." + CHECKPOINT_IDS + " LIKE CONCAT('%',"
						+ CheckpointsTable.TABLE_NAME + "." + ID + ", '%')");

		return tasks;
	}

	public boolean isCheckin(String taskId) {
		Task t = getTask(taskId);
		if (t == null) {
			log.error(taskId);
		}
		TaskContent c = getTask(taskId).getContentObject();
		if (c == null) {
			log.error(getTask(taskId));
		}
		return c.isCheckin();
	}

	private Task getTask(String taskId) {
		return getClient().readByPrimaryKey(Task.class, taskId);
	}

}
