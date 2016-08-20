package org.magcruise.citywalk.model.relation;

import java.util.List;
import java.util.stream.Collectors;

import org.magcruise.citywalk.model.row.Checkpoint;

public class CheckpointsTable extends RelationalModel<Checkpoint> {

	private static final String LAT = "LAT";
	private static final String LON = "LON";

	public static final String TABLE_NAME = "CHECKPOINTS";

	@Override
	protected String getRelationName() {
		return TABLE_NAME;
	}

	@Override
	protected String getRelationalSchema() {
		return TABLE_NAME + "(" + ID + " VARCHAR PRIMARY KEY, " + CREATED
				+ " TIMESTAMP AS CURRENT_TIMESTAMP NOT NULL, " + NAME + " VARCHAR," + INSTANCE_CLASS
				+ " VARCHAR, " + LAT + " DOUBLE, " + LON + " DOUBLE, " + CHECKPOINT_GROUP_IDS
				+ " VARCHAR)";
	}

	public List<Checkpoint> findByCheckpointGroupId(String checkpointGroupId) {
		return getClient()
				.readList(Checkpoint.class,
						"SELECT * FROM " + TABLE_NAME)
				.stream().filter(c -> c.getCheckpointGroupIds().contains(checkpointGroupId))
				.collect(Collectors.toList());
	}

}
