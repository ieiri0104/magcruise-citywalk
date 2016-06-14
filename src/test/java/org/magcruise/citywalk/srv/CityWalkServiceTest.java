package org.magcruise.citywalk.srv;

import org.junit.Test;
import org.magcruise.citywalk.jsonrpc.client.CityWalkServiceClient;
import org.magcruise.citywalk.model.Input;
import org.magcruise.citywalk.model.row.Activity;

public class CityWalkServiceTest {
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private CityWalkServiceClient client = new CityWalkServiceClient();

	@Test
	public void test() {
		log.debug(client.getActivities("ayaki"));
		client.addActivity(new Activity("ayaki", 1, 0, new Input()));
	}

}
