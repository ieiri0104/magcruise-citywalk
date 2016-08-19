package org.magcruise.citywalk.model.relation;

import java.util.List;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.row.Task;

public class TasksTable extends RelationalModel<Task> {

	public static final String TABLE_NAME = "TASKS";

	@Override
	protected String getRelationName() {
		return TABLE_NAME;
	}

	@Override
	protected String getRelationalSchema() {
		return TABLE_NAME + "(" + ID + " bigint primary key auto_increment, "
				+ CREATED + " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, "
				+ INSTANCE_CLASS + " varchar, " + CHECKPOINT_IDS + " varchar, "
				+ CONTENT + " text)";
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

	public boolean isCheckin(long taskId) {
		return getTask(taskId).getContentObject().isCheckin();
	}

	private Task getTask(long taskId) {
		return getClient().readByPrimaryKey(Task.class, taskId);
	}

}
