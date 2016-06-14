package org.magcruise.citywalk.jsonrpc.impl;

import org.junit.Before;
import org.junit.Test;
import org.magcruise.citywalk.jsonrpc.client.CityWalkServiceClient;

public class TaskServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		CityWalkServiceClient client = new CityWalkServiceClient();
		System.out.println(client.getTasks("meal"));
	}

}
