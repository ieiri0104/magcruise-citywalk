package org.magcruise.citywalk.jsonrpc.impl;

import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.TaskServiceInterface;
import org.magcruise.citywalk.model.Task;

public class TaskService extends AbstractCityWalkService
		implements TaskServiceInterface {

	@Override
	public List<Task> getTasks(String checkpointId) {
		return client.readList(Task.class,
				"SELECT * FROM " + Task.TABLE_NAME + " WHERE CHECKPOINT_ID=?",
				checkpointId);
	}

}
