package org.magcruise.citywalk.srv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.magcruise.citywalk.jsonrpc.client.CityWalkServiceClient;
import org.magcruise.citywalk.model.json.ActivityJson;

public class CityWalkServiceTest {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private CityWalkServiceClient client = new CityWalkServiceClient();

	@Test
	public void test() {
		Map<String, String> input = new HashMap<>();
		input.put("selection", "10");
		client.addActivity(new ActivityJson("ayaki", 1, input));
		ActivityJson[] as = client.getActivities("ayaki");
		log.debug(Arrays.asList(as));
		log.debug(Arrays.asList(client.getCheckpoint("cafeteria")));
		log.debug(Arrays.asList(client.getNewActivitiesOrderById("ayaki", 3)));
		log.debug(client.login("ayaki", "waseda_user"));
	}

}
