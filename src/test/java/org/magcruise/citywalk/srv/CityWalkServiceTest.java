package org.magcruise.citywalk.srv;

import org.junit.Test;
import org.magcruise.citywalk.jsonrpc.client.CityWalkServiceClient;
import org.magcruise.citywalk.model.content.Input;
import org.magcruise.citywalk.model.row.Activity;

public class CityWalkServiceTest {
	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private CityWalkServiceClient client = new CityWalkServiceClient();

	@Test
	public void test() {
		client.addActivity(new Activity("ayaki", 1, 0, new Input()));
		Activity[] as = client.getActivities("ayaki");
		log.debug(as);
		log.debug(client.getCheckpoint("cafeteria"));
		log.debug(client.getNewActivitiesOrderById("ayaki", 3));
		log.debug(client.getTasks("cafeteria"));
		log.debug(client.login("ayaki", "waseda_user"));
	}

}
