package org.magcruise.citywalk.model.table;

import java.util.List;

import org.magcruise.citywalk.model.row.Checkpoint;

public class CheckpointsTable extends TableModel<Checkpoint> {

	private static final String LAT = "LAT";
	private static final String LON = "LON";

	public CheckpointsTable() {
	}

	@Override
	protected String getTableSchema() {
		return TABLE_NAME + "(" + ID + " VARCHAR PRIMARY KEY, " + CREATED
				+ " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + INSTANCE_CLASS
				+ " VARCHAR, " + LAT + " DOUBLE, " + LON + " DOUBLE, "
				+ CHECKPOINT_GROUP_IDS + " VARCHAR)";
	}

	public List<Checkpoint> getCheckpoints(String checkPointGroupId) {
		return getClient().readList(
				Checkpoint.class, "SELECT * FROM " + TABLE_NAME + " WHERE "
						+ CHECKPOINT_GROUP_IDS + " LIKE ?",
				"%" + checkPointGroupId + "%");
	}

}
