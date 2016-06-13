package org.magcruise.citywalk.jsonrpc.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.magcruise.citywalk.jsonrpc.api.CityWalkServiceInterface;
import org.magcruise.citywalk.model.Activity;
import org.magcruise.citywalk.model.Checkpoint;
import org.magcruise.citywalk.model.Input;
import org.magcruise.citywalk.model.Task;

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
	public void login(String userId, String groupId) {
		citywalkService.login(userId, groupId);
	}

	@Override
	public Checkpoint getCheckpoint(String id) {
		return citywalkService.getCheckpoint(id);
	}

	@Override
	public void addActivity(String userId, String activityId, int score,
			Input inputs) {
		citywalkService.addActivity(userId, activityId, score, inputs);
	}

	@Override
	public void addActivity(Activity activity) {
		citywalkService.addActivity(activity);
	}

	@Override
	public List<Activity> getActivities(String userId) {
		return citywalkService.getActivities(userId);
	}

	@Override
	public List<Activity> getNewActivitiesOrderById(String userId,
			int latestActivityId) {
		return citywalkService.getNewActivitiesOrderById(userId,
				latestActivityId);
	}

	@Override
	public void testException() {
		// TODO 自動生成されたメソッド・スタブ

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
	public List<Task> getTasks(String checkpointId) {
		return citywalkService.getTasks(checkpointId);
	}

}
