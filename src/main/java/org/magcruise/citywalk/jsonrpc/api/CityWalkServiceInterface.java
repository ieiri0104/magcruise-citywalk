package org.magcruise.citywalk.jsonrpc.api;

import java.util.Map;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface CityWalkServiceInterface
		extends UserServiceInterface, CheckpointServiceInterface,
		ActivityServiceInterface, TaskServiceInterface {

	Map<String, Object> getInitialData(
			@Parameter(sample = "waseda") String checkpointGroupId);
}
