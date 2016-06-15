package org.magcruise.citywalk.jsonrpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.row.Activity;
import org.magcruise.citywalk.model.row.Checkpoint;
import org.magcruise.citywalk.model.row.Task;

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
		log.debug(client.getActivities("ayaki"));
	}

	@Override
	public boolean login(String userId, String groupId) {
		return citywalkService.login(userId, groupId);
	}

	@Override
	public Checkpoint getCheckpoint(String checkPointId) {
		return citywalkService.getCheckpoint(checkPointId);
	}

	@Override
	public Checkpoint[] getCheckpoints(String checkPointGroupId) {
		return citywalkService.getCheckpoints(checkPointGroupId);
	}

	@Override
	public void addActivity(Activity activity) {
		citywalkService.addActivity(activity);
	}

	@Override
	public Activity[] getActivities(String userId) {
		return citywalkService.getActivities(userId);
	}

	@Override
	public Activity[] getNewActivitiesOrderById(String userId,
			long latestActivityId) {
		return citywalkService.getNewActivitiesOrderById(userId,
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
	public Task[] getTasks(String checkpointId) {
		return citywalkService.getTasks(checkpointId);
	}

	@Override
	public void uploadImage(String base64EncodedImage) {
		citywalkService.uploadImage(base64EncodedImage);
	}

}
