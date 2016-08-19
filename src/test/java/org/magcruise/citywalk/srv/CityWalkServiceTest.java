package org.magcruise.citywalk.srv;

import java.util.Arrays;

import org.junit.Test;
import org.magcruise.citywalk.jsonrpc.client.CityWalkServiceClient;
import org.magcruise.citywalk.model.input.SelectionInput;
import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.json.ActivityLogJson;
import org.magcruise.citywalk.model.row.Activity;

public class CityWalkServiceTest {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private CityWalkServiceClient client = new CityWalkServiceClient();

	@Test
	public void test() {
		client.addActivity(
				new ActivityJson(
						new Activity("ayaki", "cafeteria", 35.0, 138.2, 1, 2.5,
								new SelectionInput("10"))));
		ActivityLogJson[] as = client.getActivityLogs("ayaki");
		log.debug(Arrays.asList(as));
		log.debug(Arrays.asList(client.getNewActivityLogsOrderById("ayaki", 3)));
		log.debug(client.login("ayaki", "waseda_user"));
	}

}
