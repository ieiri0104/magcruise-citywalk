package org.magcruise.citywalk.srv;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.magcruise.citywalk.controller.CityWalkServiceClient;

public class CityWalkServiceTest {
	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private static CityWalkServiceInterface client;
	static {
		URL url;
		try {
			url = new URL(
					"http://localhost:8080/MAGCruiseCityWalkServer/json/magcruise-citywalk-server");
			client = CityWalkServiceClient.create(url);
		} catch (MalformedURLException e) {
			log.error(e, e);
		}

	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		log.debug(client.getActivities("ayaki"));

	}

}
