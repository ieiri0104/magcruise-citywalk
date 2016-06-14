package org.magcruise.citywalk.jsonrpc.api;

import java.util.List;

import org.magcruise.citywalk.model.row.Checkpoint;

public interface CheckpointServiceInterface {

	Checkpoint getCheckpoint(String checkPointId);

	List<Checkpoint> getCheckpoints(String checkPointGroupId);

}
