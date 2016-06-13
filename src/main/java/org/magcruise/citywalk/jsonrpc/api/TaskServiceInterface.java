package org.magcruise.citywalk.jsonrpc.api;

import java.util.List;

import org.magcruise.citywalk.model.Task;

public interface TaskServiceInterface {

	List<Task> getTasks(String checkpointId);

}
