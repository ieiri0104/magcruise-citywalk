package org.magcruise.citywalk.jsonrpc.impl;

import org.magcruise.citywalk.jsonrpc.api.CheckpointServiceInterface;
import org.magcruise.citywalk.model.row.Checkpoint;

public class CheckpointService extends AbstractCityWalkService
		implements CheckpointServiceInterface {

	@Override
	public Checkpoint getCheckpoint(String id) {
		return getClient().readByPrimaryKey(Checkpoint.class, id);
	}

}