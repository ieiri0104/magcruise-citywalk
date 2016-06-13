package org.magcruise.citywalk.jsonrpc.api;

public interface CityWalkServiceInterface
		extends UserServiceInterface, CheckpointServiceInterface,
		ActivityServiceInterface, TaskServiceInterface {

	void testException();

}
