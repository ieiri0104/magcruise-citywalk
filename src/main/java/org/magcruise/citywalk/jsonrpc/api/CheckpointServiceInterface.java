package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.json.CheckpointJson;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface CheckpointServiceInterface {

	CheckpointJson getCheckpoint(
			@Parameter(sample = "cafeteria") String checkPointId);

	CheckpointJson[] getCheckpoints(
			@Parameter(sample = "waseda") String checkPointGroupId);

}
