package org.magcruise.citywalk.model.table;

import java.util.List;

import org.magcruise.citywalk.model.row.Task;

public class Tasks extends TableModel<Task> {

	public Tasks() {
	}

	@Override
	protected String getTableSchema() {
		return getTableName() + "(id bigint primary key auto_increment, "
				+ "checkpoint_ids varchar, " + "content text)";
	}

	public List<Task> getTasks(String checkpointId) {
		return getClient().readList(Task.class,
				"SELECT * FROM " + getTableName() + " WHERE CHECKPOINT_IDS=?",
				checkpointId);
	}

	public void insert(Task task) {
		getClient().insert(task);
	}

	public List<Task> readList(String string) {
		return getClient().readList(Task.class,
				"SELECT * from " + getTableName());
	}

}
