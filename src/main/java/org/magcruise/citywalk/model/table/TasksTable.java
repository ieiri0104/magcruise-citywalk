package org.magcruise.citywalk.model.table;

import org.magcruise.citywalk.model.row.Task;

public class TasksTable extends TableModel<Task> {

	public TasksTable() {
	}

	@Override
	protected String getTableSchema() {
		return TABLE_NAME + "(" + ID + " bigint primary key auto_increment, "
				+ CREATED + " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, "
				+ INSTANCE_CLASS + " varchar, " + CHECKPOINT_IDS + " varchar, "
				+ CONTENT + " text)";
	}

	public Task[] getTasks(String checkpointId) {
		return getClient().readList(Task.class,
				"SELECT * FROM " + TABLE_NAME + " WHERE " + CHECKPOINT_IDS
						+ " LIKE ?",
				"%" + checkpointId + "%").toArray(new Task[0]);
	}

}