package org.magcruise.citywalk.jsonrpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.json.ActivityJson;
import org.magcruise.citywalk.model.json.ActivityLogJson;
import org.magcruise.citywalk.model.json.InitialDataJson;
import org.magcruise.citywalk.model.json.RewardJson;

import jp.go.nict.langrid.client.jsonrpc.JsonRpcClientFactory;

public class CityWalkServiceClient implements CityWalkServiceInterface {

	protected static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger();

	private String url;

	private CityWalkServiceInterface citywalkService;

	public CityWalkServiceClient(String url) {
		this.url = url;
		this.citywalkService = create(CityWalkServiceInterface.class,
				"CityWalkService");
	}

	public CityWalkServiceClient() {
		this("http://localhost:8080/magcruise-citywalk-server");
	}

	public static void main(String[] args) {
		CityWalkServiceClient client = new CityWalkServiceClient();
		log.debug(client.getActivityLogs("ayaki"));
	}

	@Override
	public boolean login(String userId, String groupId) {
		return citywalkService.login(userId, groupId);
	}

	@Override
	public RewardJson addActivity(ActivityJson activity) {
		return citywalkService.addActivity(activity);
	}

	@Override
	public ActivityLogJson[] getActivityLogs(String userId) {
		return citywalkService.getActivityLogs(userId);
	}

	@Override
	public ActivityLogJson[] getNewActivityLogsOrderById(String userId,
			long latestActivityId) {
		return citywalkService.getNewActivityLogsOrderById(userId,
				latestActivityId);
	}

	public <T> T create(Class<T> clazz, String serviceName) {
		try {
			return new JsonRpcClientFactory().create(clazz,
					new URL(url + "/" + serviceName));
		} catch (MalformedURLException e) {
			log.error(e, e);
			return null;
		}
	}

	@Override
	public String uploadImage(String userId, String base64EncodedImage) {
		return citywalkService.uploadImage(userId, base64EncodedImage);
	}

	@Override
	public InitialDataJson getInitialData(String checkpointGroupId) {
		return citywalkService.getInitialData(null);
	}

	@Override
	public boolean validateCheckpointsAndTasksJson(String json) {
		return citywalkService.validateCheckpointsAndTasksJson(json);
	}

	@Override
	public InitialDataJson getInitialDataFromFile(String checkpointGroupId) {
		return citywalkService.getInitialDataFromFile(checkpointGroupId);
	}

}
