package org.magcruise.citywalk.model.table;

import java.util.List;

import org.magcruise.citywalk.model.row.Checkpoint;

public class Checkpoints extends TableModel<Checkpoint> {

	public Checkpoints() {
	}

	@Override
	protected String getTableSchema() {
		return getTableName() + "(id varchar primary key, " + "lat double, "
				+ "lon double, checkpoint_group_ids varchar)";
	}

	public void addCheckpoint(Checkpoint checkpoint) {
		getClient().insert(checkpoint);

	}

	public List<Checkpoint> getCheckPoints() {
		return getClient().readList(Checkpoint.class,
				"SELECT * from " + getTableName());
	}

}
