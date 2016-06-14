package org.magcruise.citywalk.model.table;

import org.magcruise.citywalk.model.row.Checkpoint;

public class Checkpoints extends TableModel<Checkpoint> {

	public Checkpoints() {
	}

	@Override
	protected String getTableSchema() {
		return getTableName() + "(id varchar primary key, " + "lat double, "
				+ "lon double)";
	}

}
