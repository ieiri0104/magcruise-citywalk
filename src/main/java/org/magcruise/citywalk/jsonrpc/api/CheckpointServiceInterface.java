package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.row.Checkpoint;

public interface CheckpointServiceInterface {

	Checkpoint getCheckpoint(String id);

}
