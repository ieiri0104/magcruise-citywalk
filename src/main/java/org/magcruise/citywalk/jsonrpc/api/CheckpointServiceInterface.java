package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.row.Checkpoint;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface CheckpointServiceInterface {

	Checkpoint getCheckpoint(
			@Parameter(sample = "cafeteria") String checkPointId);

	Checkpoint[] getCheckpoints(
			@Parameter(sample = "waseda") String checkPointGroupId);

}
