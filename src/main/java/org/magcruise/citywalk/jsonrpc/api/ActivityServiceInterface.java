package org.magcruise.citywalk.jsonrpc.api;

import org.magcruise.citywalk.model.row.Activity;

import jp.go.nict.langrid.commons.rpc.intf.Parameter;

public interface ActivityServiceInterface {

	void addActivity(
			@Parameter(sample = "{\"instanceClass\": \"org.magcruise.citywalk.model.row.Activity\","
					+ " \"userId\": \"ayaki\", " + "\"taskId\": \"task2\", "
					+ "\"score\": 9.0, " + "\"input\": "
					+ "{\"instanceClass\":\"org.magcruise.citywalk.model.content.SelectionInput\",\"value\":\"1\"}}") Activity activity);

	Activity[] getActivities(@Parameter(sample = "ayaki") String userId);

	Activity[] getNewActivitiesOrderById(String userId,
			long latestActivityId);

}
