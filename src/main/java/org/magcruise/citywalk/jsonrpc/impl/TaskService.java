package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.TaskServiceInterface;
import org.magcruise.citywalk.model.row.Task;
import org.magcruise.citywalk.model.table.Tasks;

public class TaskService extends AbstractCityWalkService
		implements TaskServiceInterface {

	@Override
	public List<Task> getTasks(String checkpointId) {
		return new Tasks().getTasks(checkpointId);
	}

}
