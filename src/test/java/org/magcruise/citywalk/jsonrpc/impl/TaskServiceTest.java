package org.magcruise.citywalk.jsonrpc.impl;

import org.junit.Before;
import org.junit.Test;
import org.magcruise.citywalk.jsonrpc.client.CityWalkServiceClient;

public class TaskServiceTest {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		CityWalkServiceClient client = new CityWalkServiceClient();
		log.debug(client.getCheckpoints("waseda"));
		log.debug(client.getCheckpoint("cafeteria"));
		log.debug(client.getTasks("cafeteria"));
	}

}
