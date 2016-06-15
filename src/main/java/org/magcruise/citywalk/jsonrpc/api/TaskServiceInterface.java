package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.row.Task;

public interface TaskServiceInterface {

	Task[] getTasks(String checkpointId);

}
